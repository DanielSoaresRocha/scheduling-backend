package com.example.scheduling.service;

import com.example.scheduling.dto.ContatoFilterDTO;
import com.example.scheduling.model.Contato;
import com.example.scheduling.repository.ContatoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ContatoServiceTest {

    @Mock
    private ContatoRepository contatoRepository;

    @InjectMocks
    private ContatoService contatoService;

    private Contato contato;
    private ContatoFilterDTO contatoFilterDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        contato = new Contato();
        contato.setId(1L);
        contato.setNome("João");
        contato.setCelular("123456789");
        contato.setEmail("joao@example.com");
        contato.setTelefone("987654321");
        contato.setFavorito("S");
        contato.setAtivo("S");
        contato.setDateCadastro(LocalDateTime.now());

        contatoFilterDTO = new ContatoFilterDTO();
    }

    @Test
    void testListarTodos() {
        Mockito.when(contatoRepository.findByOrderByNomeAsc()).thenReturn(List.of(contato));

        var contatos = contatoService.listarTodos();

        assertNotNull(contatos);
        assertEquals(1, contatos.size());
        assertEquals("João", contatos.get(0).getNome());
    }

    @Test
    void testBuscarPorId() {
        Mockito.when(contatoRepository.findById(1L)).thenReturn(Optional.of(contato));

        var result = contatoService.buscarPorId(1L);

        assertTrue(result.isPresent());
        assertEquals("João", result.get().getNome());
    }

    @Test
    void testSalvar() {
        Mockito.when(contatoRepository.findByCelular("123456789")).thenReturn(null);
        Mockito.when(contatoRepository.save(contato)).thenReturn(contato);

        var savedContato = contatoService.salvar(contato);

        assertNotNull(savedContato);
        assertEquals("João", savedContato.getNome());
        assertEquals(LocalDateTime.now().getDayOfYear(), savedContato.getDateCadastro().getDayOfYear());  // Verifica se o cadastro tem a data de hoje
    }

    @Test
    void testSalvarComCelularExistente() {
        Mockito.when(contatoRepository.findByCelular("123456789")).thenReturn(contato);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            contatoService.salvar(contato);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Já existe um contato com este número de celular.", exception.getReason());
    }

    @Test
    void testAtualizar() {
        Contato contatoAtualizado = new Contato();
        contatoAtualizado.setNome("Carlos");
        contatoAtualizado.setEmail("carlos@example.com");
        contatoAtualizado.setCelular("987654321");
        contatoAtualizado.setTelefone("123456789");
        contatoAtualizado.setFavorito("N");
        contatoAtualizado.setAtivo("N");

        Mockito.when(contatoRepository.findById(1L)).thenReturn(Optional.of(contato));
        Mockito.when(contatoRepository.save(Mockito.any(Contato.class))).thenReturn(contatoAtualizado);

        var updatedContato = contatoService.atualizar(1L, contatoAtualizado);

        assertNotNull(updatedContato);
        assertEquals("Carlos", updatedContato.getNome());
        assertEquals("carlos@example.com", updatedContato.getEmail());
        assertEquals("987654321", updatedContato.getCelular());
        assertEquals("N", updatedContato.getFavorito());
    }

    @Test
    void testAtualizarContatoNaoEncontrado() {
        Contato contatoAtualizado = new Contato();
        contatoAtualizado.setNome("Carlos");

        Mockito.when(contatoRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            contatoService.atualizar(1L, contatoAtualizado);
        });

        assertEquals("Contato não encontrado", exception.getMessage());
    }

    @Test
    void testDeletar() {
        Mockito.doNothing().when(contatoRepository).deleteById(1L);

        assertDoesNotThrow(() -> contatoService.deletar(1L));

        Mockito.verify(contatoRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void testFiltrarContatos() {
        Mockito.when(contatoRepository.filtrarContatos(contatoFilterDTO)).thenReturn(List.of(contato));

        var filteredContatos = contatoService.filtrarContatos(contatoFilterDTO);

        assertNotNull(filteredContatos);
        assertEquals(1, filteredContatos.size());
        assertEquals("João", filteredContatos.get(0).getNome());
    }
}