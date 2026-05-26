import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Pages.EvolutionPage;

public class EvolutionTest extends BaseTest{
    EvolutionPage evolutionPage;
    @BeforeMethod
    public void navigateToEvolution() {
        evolutionPage = new EvolutionPage();
        evolutionPage.open(); // driver.get("http://localhost:5173/pacientes")
        evolutionPage.goToEvolution();
    }

    @Test(description = "Añadir nota de evolución a paciente")
    public void addEvolutionNote(){
        evolutionPage.addEvolutionNote("Pata rota","Cirugia de rodilla","90","175","Ejercicios de rodilla semanales","Paracetamol");
        Assert.assertEquals(evolutionPage.getToastTitle(),"Nota de evolución guardada");
    }

    @Test(description = "Actualización de nota de evolución")
    public void updateEvolutionNote(){
        evolutionPage.updateEvolutionNote("Actualización de nota de evolución");
        Assert.assertEquals(evolutionPage.getToastTitle(),"Nota de evolución actualizada");
    }

    @Test
    public void addEmptyEvolutionNote(){
        evolutionPage.addEmptyEvolutionNote();
        Assert.assertTrue(evolutionPage.getToastTitle().contains("Revisa los campos del formulario"));
    }
}
