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

public class WhenDoCreateFor {

    AppiumDriver android;

    @BeforeEach
    public void openApp() throws MalformedURLException {

        System.out.println("Se está abriendo la app");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:deviceName","ANDROID9");
        capabilities.setCapability("appium:platformVersion","9.0");
        capabilities.setCapability("appium:appPackage","com.vrproductiveapps.whendo");
        capabilities.setCapability("appium:appActivity","com.vrproductiveapps.whendo.ui.HomeActivity");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("appium:automationName","uiautomator2");
        android = new AndroidDriver(new URL("http://192.168.18.13:4723/"),capabilities);

        //Espera máximo 30 segundos por cada localizador
        android.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void createATask() throws InterruptedException {

        for (int i = 0; i < 30; i++) {
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
    }
}
