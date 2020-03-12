package web;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.xml.internal.ws.client.RequestContext;
import domain.Material;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import service.MaterialService;

@SessionScoped
@Named("materialBean")

public class MaterialBean implements Serializable {

    int id;

    Logger log = LogManager.getRootLogger();

    @Inject
    private MaterialService materialService;

    private Material materialSeleccionado;

    List<Material> material;
    List<Material> materialResuardo;

    public MaterialBean() {
        log.debug("Iniciando el objeto PersonaBean");
    }

    @PostConstruct
    public void inicializar() {
        //Inciamos las variables
        this.material = materialService.listarMaterial();
        log.debug("personas recuperadas en ManagedBean:" + this.material);
        this.materialSeleccionado = new Material();
        this.materialResuardo = new ArrayList<>();
    }

    public void editListener(RowEditEvent event) {
        Material material = (Material) event.getObject();

    }

    public Material getMaterialSeleccionado() {
        return materialSeleccionado;
    }

    public void setMaterialSeleccionado(Material materialSeleccionado) {
        this.materialSeleccionado = materialSeleccionado;
    }

    public List<Material> getMaterial() {
        return material;
    }

    public void setMaterial(List<Material> material) {
        this.material = material;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Material> getMaterialResuardo() {
        return materialResuardo;
    }

    public void setMaterialResuardo(List<Material> materialResuardo) {
        this.materialResuardo = materialResuardo;
    }

    public void agregarMaterial() {
        if (getMaterialResuardo().isEmpty()) {
            materialSeleccionado = materialService.encontrarMaterialPorId(id);

            materialResuardo.add(0, materialSeleccionado);
        } else {
            materialSeleccionado = materialService.encontrarMaterialPorId(id);

            materialResuardo.add(getMaterialResuardo().size(), materialSeleccionado);
        }

        setMaterialResuardo(materialResuardo);

        System.out.println("el tamaño de la lista es de " + getMaterialResuardo().size());
        this.materialSeleccionado = new Material();
        this.id = 0;
    }

    public void limpiar() {
        materialResuardo.clear();
    }

    public void createPDF() throws JRException {
        //Agregamos el objeto empleado a la lista

        JasperReport reporte; // Instaciamos el objeto reporte
        String path = "C:\\Users\\Edgar David\\JaspersoftWorkspace\\MyReports\\ReporteR.jasper"; //Ponemos la localizacion del reporte creado
    try{
        reporte = (JasperReport) JRLoader.loadObject(path); //Se carga el reporte de su localizacion
        JasperPrint jprint;

        jprint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(materialResuardo)); //Agregamos los parametros para llenar el reporte

        
        JasperViewer viewer = new JasperViewer(jprint, false); //Se crea la vista del reportes

        viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Se declara con dispose_on_close para que no se cierre el programa cuando se cierre el reporte

        viewer.setVisible(true); //Se vizualiza el reporte
} catch (JRException ex) {
           
        }
    }
    
    public void imprimir() {
             
             Document document = new Document(PageSize.LETTER);
             ByteArrayOutputStream baos = new ByteArrayOutputStream();
             try {
                   PdfWriter.getInstance(document, baos);
                  //METADATA
                   
                  document.open();
                 
                  document.add(new Paragraph(" Reporte de resguardo\n"));
                 
                  DateFormat formatter= new SimpleDateFormat("dd/MM/yy '-' hh:mm:ss:");
                   Date currentDate = new Date();
                   String date = formatter.format(currentDate);
                  document.add(new Paragraph("Fecha Generado: "+date)); 
                  document.add(new Paragraph("\n"));
                 
                  PdfPTable table = new PdfPTable(5);
                 
                  table.setTotalWidth(new float[]{ 25,100, 110, 95, 170,});
              table.setLockedWidth(true);
               PdfPCell cell = new PdfPCell(new Paragraph ("idmaterial", FontFactory.getFont("arial",8,Font.BOLD,BaseColor.GRAY )));
                  table.addCell("Id");
                  table.addCell("numeroSIL");
                  table.addCell("descripcion");
                  table.addCell("valor");
                  table.addCell("observacion");
                 
                  for (int i = 0; i < materialResuardo.size(); i++) {
                        Material id = materialResuardo.get(i);
                        table.addCell(id.getIdmaterial().toString());
                        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        table.addCell(id.getNumeroSIL());
                        table.addCell(id.getDescripcion());
                        table.addCell(id.getValor().toString());
                        table.addCell(id.getObservacion());
                  }
                  document.add(table);
                  document.add(new Paragraph("\n"));
                  document.add(new Paragraph("Total de materiales entregados: "+materialResuardo.size()));
                  document.add(new Paragraph("\n"));
                  document.add(new Paragraph("\n"));
                  PdfPTable tableFirmas = new PdfPTable(2);
                 
                  tableFirmas.setTotalWidth(new float[]{ 100,100});
              table.setLockedWidth(true);
                  tableFirmas.addCell("Entrego: _______________________");
                  tableFirmas.addCell("Recibio: _______________________");
                  document.add(tableFirmas);
            } catch (Exception ex) {
                  System.out.println("Error " + ex.getMessage());
            }
            document.close();
            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();
            if (response instanceof HttpServletResponse) {
                  HttpServletResponse hsr = (HttpServletResponse) response;
                  hsr.setContentType("application/pdf");
                  hsr.setHeader("Content-disposition", "attachment");
                  hsr.setContentLength(baos.size());
                  try {
                        ServletOutputStream out = hsr.getOutputStream();
                        baos.writeTo(out);
                        out.flush();
                  } catch (IOException ex) {
                        System.out.println("Error:  " + ex.getMessage());
                  }
                  context.responseComplete();
            }
       }
       
       
}
