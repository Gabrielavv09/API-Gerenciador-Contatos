package com.example.gerenciadorcontatos.controller;
// teste
import com.example.gerenciadorcontatos.model.Contact;
import com.example.gerenciadorcontatos.service.ContactService;
import org.springframework.http.ResponseEntity; // <-- IMPORTAÇÃO ADICIONADA
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContactController {

    private final ContactService contactService;

    // Injeção de dependência do serviço
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // GET /contatos - Lista todos os contatos
    // Para esta operação, a lista completa deve ser retornada com status 200 OK.
    // Não precisa de tratamento de erro, pois a lista vazia é um retorno válido.
    @GetMapping
    public ResponseEntity<List<Contact>> listar() {
        return ResponseEntity.ok(contactService.listar());
    }

    // GET /contatos/{index} - Busca um contato pelo índice
    @GetMapping("/{index}")
    public ResponseEntity<Contact> buscar(@PathVariable int index) {
        Contact contato = contactService.buscar(index);
        if (contato != null) {
            // Se o contato for encontrado, retorna o status 200 OK.
            return ResponseEntity.ok(contato);
        } else {
            // Se não for encontrado, retorna o status 404 NOT FOUND.
            return ResponseEntity.notFound().build();
        }
    }

    // POST /contatos - Adiciona um novo contato
    // Para esta operação, o status 201 CREATED deve ser retornado.
    @PostMapping
    public ResponseEntity<Contact> adicionar(@RequestBody Contact c) {
        // Você pode adicionar uma validação básica aqui, como nome e e-mail não nulos.
        // Se a validação falhar, retorne 400 BAD REQUEST.
        if (c.getNome() == null || c.getEmail() == null || c.getTelefone() == null) {
            return ResponseEntity.badRequest().build();
        }
        Contact novoContato = contactService.adicionar(c);
        // Retorna o contato criado com o status 201 CREATED.
        return ResponseEntity.status(201).body(novoContato);
    }

    // PUT /contatos/{index} - Atualiza um contato pelo índice
    @PutMapping("/{index}")
    public ResponseEntity<Contact> atualizar(@PathVariable int index, @RequestBody Contact c) {
        // Validação básica para o corpo da requisição
        if (c.getNome() == null || c.getEmail() == null || c.getTelefone() == null) {
            return ResponseEntity.badRequest().build();
        }

        Contact contatoAtualizado = contactService.atualizar(index, c);
        if (contatoAtualizado != null) {
            // Se a atualização for bem-sucedida, retorna 200 OK.
            return ResponseEntity.ok(contatoAtualizado);
        } else {
            // Se o índice não existir, retorna 404 NOT FOUND.
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /contatos/{index} - Remove um contato pelo índice
    @DeleteMapping("/{index}")
    public ResponseEntity<Contact> remover(@PathVariable int index) {
        Contact contatoRemovido = contactService.remover(index);
        if (contatoRemovido != null) {
            // Se o contato foi removido, retorna 204 NO CONTENT.
            return ResponseEntity.noContent().build();
        } else {
            // Se o índice não existir, retorna 404 NOT FOUND.
            return ResponseEntity.notFound().build();
        }
    }
}