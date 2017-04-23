package hr.diskobolos.model;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * Main JUnit test for model entity classes
 *
 * @author Tomislav Čavka
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ModelEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void fetchAllBankAccounts() throws Exception {
        TypedQuery<BankAccount> query = this.entityManager.getEntityManager().createNamedQuery("BankAccount.findAll",
                BankAccount.class);
        List<BankAccount> result = query.getResultList();
        assertNotNull(result);
    }

    @Test
    public void fetchAllEmails() throws Exception {
        TypedQuery<Email> query = this.entityManager.getEntityManager().createNamedQuery("Email.findAll",
                Email.class);
        List<Email> result = query.getResultList();
        assertNotNull(result);
    }

    @Test
    public void fetchAllMemberRegisters() throws Exception {
        TypedQuery<MemberRegister> query = this.entityManager.getEntityManager().createNamedQuery("MemberRegister.findAll",
                MemberRegister.class);
        List<MemberRegister> result = query.getResultList();
        assertNotNull(result);
    }

}
