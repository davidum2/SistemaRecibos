/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Material;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Edgar David
 */
@Local
public interface MaterialService {
    
    
      public List<Material> listarMaterial();
    
    public Material encontrarMaterialPorId(int id);
    
    
}
