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

public class WhenDoCreateDelete {

    AppiumDriver android;
    public static String titulo =  "JB"+ new Date().getTime();

    @BeforeEach
    public void openApp() throws MalformedURLException, InterruptedException {

        System.out.println("La tarea creada es :"+titulo);

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
        android.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Tener la tarea creada
        //Creando tarea
        System.out.println("Se ejecuta el create Task");

        //String titulo = "JB"+ new Date().getTime();

        // add task +
        android.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
        // fill titulo
        android.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys(titulo);
        // fill des
        android.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys("this is a note");
        // click save
        android.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();

        //verificar
        //String actualResult =android.findElement(By.id("com.vrproductiveapps.whendo:id/home_list_item_text")).getText();
        String actualResult =android.findElement(By.xpath("//android.widget.TextView[@text='"+titulo+"']")).getText();

        String expectedResult = titulo;
        Assertions.assertEquals(expectedResult,actualResult,"ERROR ! la tarea no se creo correctamente");

        Thread.sleep(2000);
    }

    @Test
    public void deleteATask() throws InterruptedException {

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
