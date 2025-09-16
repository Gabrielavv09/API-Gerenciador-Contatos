package com.example.gerenciadorcontatos.service;

// Adicionar, remover, buscar e atualizar
import com.example.gerenciadorcontatos.model.Contact;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    private final List<Contact> contatos = new ArrayList<>();

    // Adiciona um novo contato
    public Contact adicionar(Contact c) {
        contatos.add(c);
        return c;
    }

    // Retorna todos os contatos
    public List<Contact> listar() {
        return contatos;
    }

    // Busca um contato pelo índice
    public Contact buscar(int index) {
        if (index >= 0 && index < contatos.size()) {
            return contatos.get(index);
        }
        return null;
    }

    // Remove um contato pelo índice
    public Contact remover(int index) {
        if (index >= 0 && index < contatos.size()) {
            return contatos.remove(index);
        }
        return null;
    }

    // Atualiza um contato pelo índice
    public Contact atualizar(int index, Contact c) {
        if (index >= 0 && index < contatos.size()) {
            contatos.set(index, c);
            return c;
        }
        return null;
    }
}