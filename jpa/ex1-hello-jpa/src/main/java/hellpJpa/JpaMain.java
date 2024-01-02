package hellpJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
//            Member member = new Member();
//            member.setUsername("A");
//            member.setRoleType(RoleType.USER);
//
//            em.persist(member);
            //저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Human human = new Human();
            human.setName("human1");
            human.setTeam(team);
            em.persist(human);

            Human findHuman = em.find(Human.class, human.getId());
            List<Human> humans = findHuman.getTeam().getHumans();

            for (Human h : humans) {
                System.out.println(h.getName());
            }

            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
