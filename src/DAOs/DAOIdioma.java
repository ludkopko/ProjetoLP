package DAOs;

import Entidades.Idioma;
import java.util.ArrayList;
import java.util.List;

public class DAOIdioma extends DAOGenerico<Idioma> {

    public DAOIdioma() {
        super(Idioma.class);
    }

    public int autoIdIdioma() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idIdioma) FROM Idioma e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }public List<Idioma> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Idioma e WHERE e.nomeIdioma LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Idioma> listById(int id) {
        return em.createQuery("SELECT e FROM Idioma e WHERE e.idIdioma = :id").setParameter("id", id).getResultList();
    }

    public List<Idioma> listInOrderNome() {
        return em.createQuery("SELECT e FROM Idioma e ORDER BY e.nomeIdioma").getResultList();
    }

    public List<Idioma> listInOrderId() {
        return em.createQuery("SELECT e FROM Idioma e ORDER BY e.idIdioma").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Idioma> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdIdioma() + "-" + lf.get(i).getNomeIdioma());
        }
        return ls;
    }
}

