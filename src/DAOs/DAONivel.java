package DAOs;

import Entidades.Nivel;
import java.util.ArrayList;
import java.util.List;

public class DAONivel extends DAOGenerico<Nivel> {

    public DAONivel() {
        super(Nivel.class);
    }

    public int autoIdNivel() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idNivel) FROM Nivel e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Nivel> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Nivel e WHERE e.nomeNivel LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Nivel> listById(int id) {
        return em.createQuery("SELECT e FROM Nivel e WHERE e.idNivel = :id").setParameter("id", id).getResultList();
    }

    public List<Nivel> listInOrderNome() {
        return em.createQuery("SELECT e FROM Nivel e ORDER BY e.nomeNivel").getResultList();
    }

    public List<Nivel> listInOrderId() {
        return em.createQuery("SELECT e FROM Nivel e ORDER BY e.idNivel").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Nivel> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdNivel() + "-" + lf.get(i).getNomeNivel());
        }
        return ls;
    }
}
