package com.curso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.domains.Cidade;
import com.curso.domains.Cliente;
import com.curso.domains.Estado;
import com.curso.domains.Funcionario;
import com.curso.domains.Itemvenda;
import com.curso.domains.Livro;
import com.curso.domains.Venda;
import com.curso.domains.enums.Status;
import com.curso.repositories.CidadeRepository;
import com.curso.repositories.ClienteRepository;
import com.curso.repositories.EstadoRepository;
import com.curso.repositories.FuncionarioRepository;
import com.curso.repositories.ItemvendaRepository;
import com.curso.repositories.LivroRepository;
import com.curso.repositories.VendaRepository;

@Service
public class DBService {

    @Autowired
    private EstadoRepository estadoRepo;

    @Autowired
    private CidadeRepository cidadeRepo;

    @Autowired
    private FuncionarioRepository funcRepo;

    @Autowired
    private ClienteRepository cliRepo;    

    @Autowired
    private LivroRepository livroRepo;

    @Autowired
    private VendaRepository vendaRepo;   
    
    @Autowired
    private ItemvendaRepository itRepo;    
    

    public void initDB()
    {
        Estado est01 = new Estado(null, "São Paulo", "SP");
        Estado est02 = new Estado(null, "Minas Gerais", "MG");
        estadoRepo.save(est01);
        estadoRepo.save(est02);
        
        Cidade cid01 = new Cidade(null, "Fernandópolis", est01, Status.ATIVO);
        Cidade cid02 = new Cidade(null, "Votuporanga", est01, Status.ATIVO);
        Cidade cid03 = new Cidade(null, "Carneirinho", est02, Status.ATIVO);
        Cidade cid04 = new Cidade(null, "Iturama", est02, Status.ATIVO);
        cidadeRepo.save(cid01);
        cidadeRepo.save(cid02);
        cidadeRepo.save(cid03);
        cidadeRepo.save(cid04);

        Funcionario fun01 = new Funcionario(null, "Luiza Fernanda", "12.123.123-1", "123.123.123.12", "Rua Terra Seca", "123", "Tudo Seco", "12.123-1234", "luiza@algumlugar.com", "123", "123456", cid01, Status.ATIVO);
        Funcionario fun02 = new Funcionario(null, "Gabriel", "42.553.153-1", "188.193.123.12", "Rua Terra Seca Nova", "123", "Tudo Seco Dinovo", "12.123-1234", "gabriel@algumlugar.com", "123", "123456", cid03, Status.ATIVO);
        funcRepo.save(fun01);
        funcRepo.save(fun02);

        Cliente cli01 = new Cliente(null, "Luiza Fernanda Cliente", "13.143.923-1", "155.143.123.12", "Rua Terra Seca", "123", "Tudo Seco", "12.123-1234", "luiza_cliente@algumlugar.com", "123456", cid01, Status.ATIVO);
        cliRepo.save(cli01);

        Livro liv01 = new Livro(null, "Como Ganhar na Mega-Sena", "23445342342", "Luiza", "Tamo Junto", "0001");
        livroRepo.save(liv01);

        Venda ven01 = new Venda(null, "Pago a Vista", 10.23, cli01, fun01);
        vendaRepo.save(ven01);

        Itemvenda itv01 = new Itemvenda(null, liv01, ven01, 1, 10.23, 10.23);
        itRepo.save(itv01);        
        
    }
}
