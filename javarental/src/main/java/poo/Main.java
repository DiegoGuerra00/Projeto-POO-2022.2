package poo;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import poo.models.Carro;
import poo.models.Motocicleta;
import poo.models.Usuario;

public class Main {
    public static void main(String[] args) throws ParseException {
        // Criação dos mocks de usuario (sem persistência)
        Usuario mock1= new Usuario();
        mock1.setEmail("mock1@teste.com");
        mock1.setNomeUsuario("mock_1");
        mock1.setNome("mock1");
        mock1.setSenha("teste");
        mock1.setCpf("00000000000");

        Usuario mock2= new Usuario();
        mock2.setEmail("mock2@teste.com");
        mock2.setNomeUsuario("mock_2");
        mock2.setNome("mock2");
        mock2.setSenha("teste");
        mock2.setCpf("11111111111");

        Usuario user = new Usuario();
        user.setEmail("teste@teste.com");
        user.setNomeUsuario("user_3");
        user.setNome("User");
        user.setSenha("senha");
        user.setCpf("123");

        Usuario user1 = new Usuario();
        user1.setEmail("email@email.com");
        user1.setNomeUsuario("user_2");
        user1.setNome("name");
        user1.setSenha("pwd");
        user1.setCpf("321");
        
        // Criação do EMF passado como param
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
    
        // Criação dos mocks de carro (sem persistência)
        Carro car = new Carro();
        car.setModelo("Honda Civic");
        car.setDisponivel(true);
        car.setAno(2010);
        car.setCor("azul");
        car.setPrecoDiario(300.45);

        Carro car1 = new Carro();
        car1.setModelo("Honda Civic");
        car1.setDisponivel(true);
        car1.setAno(2005);
        car1.setCor("vermelha");
        car1.setPrecoDiario(150.45);

        Carro car2 = new Carro();
        car2.setAno(1111);
        car2.setCor("amarelo");
        car2.setPrecoDiario(450.30);

        Carro car3 = new Carro();
        car3.setAno(2222);
        car3.setCor("verde");
        car3.setPrecoDiario(222.34);

        // Criação dos mocks de moto (sem persistência)
        Motocicleta moto = new Motocicleta();
        moto.setAno(2005);
        moto.setCor("vermelha");
        moto.setPrecoDiario(130.40);

        Motocicleta moto1 = new Motocicleta();
        moto1.setAno(9999);
        moto1.setCor("amarela");
        moto1.setPrecoDiario(250.34);

        Motocicleta moto2 = new Motocicleta();
        moto2.setAno(7777);
        moto2.setCor("roxa");
        moto2.setPrecoDiario(390.82);

        Motocicleta moto3 = new Motocicleta();
        moto3.setAno(0000);
        moto3.setCor("rosa");
        moto3.setPrecoDiario(184.32);
        
        // Criação dos mocks de datas (sem persistência)
        LocalDate inicio = LocalDate.now();
        LocalDate fim = LocalDate.of(2022, 9, 4);

        // Teste 1: Persistência do Aluguel de um carro (car) com usuário 'user'
        user.efetuarAluguel(car, null, user, inicio, fim, em);

        // Teste 2: Persistência do Aluguel de um carro (car1) com usuário 'user'
        user.efetuarAluguel(car1, null, user, inicio, fim, em);
        
        // Teste 3: Persistência do Aluguel de uma moto (moto) com usuário 'user'
        user.efetuarAluguel(null, moto, user, inicio, fim, em);

        // Teste 4: Persistência do Aluguel de uma moto (moto1) com usuário 'user'
        user.efetuarAluguel(null, moto, user, inicio, fim, em);

        // Teste de query de busca de resultados na tabela
        String queryText = "FROM Carro WHERE modelo = :modelo AND cor = :cor AND ano = :ano AND assentos = :assentos AND isDisponivel = true";
            try {
                Query query = em.createQuery(queryText);
                query.setParameter("modelo", "Honda Civic");
                query.setParameter("cor", "azul");
                query.setParameter("ano", 2010);
                query.setParameter("assentos", 4);

                List<Carro> carro_result = (List<Carro>) query.getResultList();
                System.out.println(carro_result.size());
                System.out.println("modelo: " +carro_result.get(0).getCor());
            } catch (NoResultException e) {

            }
        
        // Auth auth = new Auth();
        // auth.login("user_1", "senha");
        // auth.login("user_2", "outra_senha");
    
        em.close();
        emf.close();
    }
}
