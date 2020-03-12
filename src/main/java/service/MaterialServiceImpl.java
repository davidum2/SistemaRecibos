/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import datos.MaterialDao;
import domain.Material;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Edgar David
 */
@Stateless
public class MaterialServiceImpl implements MaterialService{

    @Inject
    private MaterialDao materialDao;

    
    @Override
    public List<Material> listarMaterial() {
      return materialDao.findAll();
    }

    @Override
    public Material encontrarMaterialPorId(int id) {
        return materialDao.findById(id);
    }
    
}
