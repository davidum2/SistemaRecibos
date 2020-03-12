/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import domain.Material;
import java.util.List;

/**
 *
 * @author Edgar David
 */
public interface MaterialDao {
    
    public List<Material> findAll();
    public Material findById(int id);
    
}
