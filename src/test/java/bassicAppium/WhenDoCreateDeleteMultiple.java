package bassicAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.Random;

public class WhenDoCreateDeleteMultiple {

    AppiumDriver android;
    public static String titulo =  "JB"+ new Date().getTime();

    @BeforeEach
    public void openApp() throws MalformedURLException, InterruptedException {

        for (int i = 0; i < 5; i++) {
            try {
                Random random = new Random();
                // Generar un número aleatorio entre 0 (inclusive) y 100 (exclusivo)
                int numeroAleatorio = random.nextInt(100);
                String titulo = "Prueba"+numeroAleatorio;

                android.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
                android.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys(titulo);
                android.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys("Es una prueba");
                android.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();

                String actualResult = android.findElement(By.xpath("//android.widget.TextView[@text='" + titulo + "']")).getText();
                String expectedResult = titulo;
                Assertions.assertEquals(expectedResult, actualResult, "ERROR: La tarea no se creo correctamente");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Thread.sleep(2000);
    }

    @Test
    public void deleteATaskMultiple() throws InterruptedException {

        //Ingresamos a la tarea que queremeos eliminar
        android.findElement(By.xpath("//android.widget.TextView[@text='"+titulo+"']")).click();
        //Clic en el ícono Eliminar
        android.findElement(By.id("com.vrproductiveapps.whendo:id/deleteItem")).click();
        //Confirmamos la eliminación
        android.findElement(By.xpath("//android.widget.Button[@text=\"DELETE\"]")).click();

        //Verificar
        Assertions.assertTrue(android.findElement(By.xpath("//android.widget.TextView[@text=\"No tasks added\"]")).isDisplayed());

        Thread.sleep(2000);

    }

    @AfterEach
    public void cerrarApp(){
        System.out.println("Cerraremos la app");
        android.quit();
    }

}
