package DAOs;

import static DAOs.DAOGenerico.em;
import Entidades.Horario;
import java.util.ArrayList;
import java.util.List;

public class DAOHorario extends DAOGenerico<Horario> {

    public DAOHorario() {
        super(Horario.class);
    }

    public int autoIdHorario() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idHorario) FROM Horario e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }public List<Horario> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Horario e WHERE e.nomeHorario LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Horario> listById(int id) {
        return em.createQuery("SELECT e FROM Horario e WHERE e.idHorario = :id").setParameter("id", id).getResultList();
    }

    public List<Horario> listInOrderNome() {
        return em.createQuery("SELECT e FROM Horario e ORDER BY e.nomeHorario").getResultList();
    }

    public List<Horario> listInOrderId() {
        return em.createQuery("SELECT e FROM Horario e ORDER BY e.idHorario").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Horario> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdHorario() + "-" + lf.get(i).getCodigoHorario());
        }
        return ls;
    }
}

