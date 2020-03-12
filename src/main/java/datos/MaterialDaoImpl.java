package datos;

import domain.Material;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Edgar David
 */
public class MaterialDaoImpl implements MaterialDao{

    @PersistenceContext(unitName="resguardo")
    EntityManager em;
    
    @Override
    public List<Material> findAll() {
        return em.createNamedQuery("Material.findAll").getResultList();

    }

    @Override
    public Material findById(int id) {
        Query q = em.createNativeQuery("SELECT * FROM resguardo.material WHERE material.idmaterial ="+id, Material.class);
        Material material=(Material) q.getSingleResult();
        return material;

    }
    
}
