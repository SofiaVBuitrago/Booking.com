package StepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Booking {
    WebDriver driver = null;

    @Given("The user is on the booking page")
    public void the_user_is_on_the_booking_page() {
        String edgePath = "C:\\Users\\sofia.buitrago\\Documents\\Booking\\src\\test\\resources\\Drivers\\msedgedriver.exe";
        System.setProperty("webdriver.edge.driver",edgePath);
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.booking.com/");
    }
    @And("the user selects the city and Dates")
    public void the_user_selects_the_city_and_dates() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.findElement(By.className("f0298d74a8")).click();
        driver.findElement(By.name("ss")).sendKeys("Bogotá");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@id='autocomplete-result-0']")).click();
        /*acá se abre el calendar*/
        String checkInMonth = "August 2024";
        String checkInDay = "5";
        String checkOutDay = "15";
        /*acá cogemos el mes de la derecha y el de la izquierda*/
        String monthLeft = driver.findElement(By.xpath("(//h3[@class='c91d26f0de c6cf04c67e'])[1]")).getText();
        String monthRight = driver.findElement(By.xpath("(//h3[@class='c91d26f0de c6cf04c67e'])[2]")).getText();
        /*aca validamos si el de la iz o der es mi mismo checkinmonth, sino, le damos a la flecha siguiente*/
        if (monthLeft.contains(checkInMonth) || monthRight.contains(checkInMonth)) {
            System.out.println("es el mismo mes");
            } else {
                driver.findElement(By.xpath("//body/div[@id='indexsearch']/div[2]/div[1]/form[1]/div[1]/div[2]/div[1]/div[2]/div[1]/nav[1]/div[2]/div[1]/div[1]/button[1]")).click();
                 }

        /*acá sacamos el xpath,ese xpath va a trae 31 resultados (días) entonces lo metemos en una lista*/
        List<WebElement> dayList= driver.findElements(By.xpath("(//tbody)[2]//tr//td/span"));
        /*aca para cada elemento de la lista, sacamos el texto, en el if, si alguno es el dia de checkin que puse, clickeelo y rompa el ciclo*/
        for (WebElement elementDays : dayList){
              String dayGetText = elementDays.getText();
              if(dayGetText.equals(checkInDay)){
                  elementDays.click();
                  break;
              }
        }
        /*check out*/
        for (WebElement elementDays : dayList){
            String dayGetText = elementDays.getText();
            if(dayGetText.equals(checkOutDay)){
                elementDays.click();
                break;
            }
        }
        driver.findElement(By.className("c4255fc1c2")).click();


    }
    @When("the places are displayed the user marks the five stars and chapinero filters")
    public void the_places_are_displayed_the_user_marks_the_stars_and_chapinero_filters() throws InterruptedException {


            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
            System.out.println("va a validar titulo");
            wait.until(ExpectedConditions.titleContains("Hotels in Bogotá"));
            System.out.println("hace la espera");
            driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
            WebElement fiveStars = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@data-filters-group=\"class\"]//div[@data-filters-item=\"class:class=3\"]//div[@data-testid=\"filters-group-label-content\"]/div)[1]")));
            System.out.println("hizo la espera");
            // fiveStars.click();
            driver.findElement(By.xpath("(//div[@data-filters-group=\"class\"]//div[@data-filters-item=\"class:class=5\"]//div[@data-testid=\"filters-group-label-content\"]/div)[1]")).click();


            wait.until(ExpectedConditions.titleContains("Hotels in Bogotá"));
            driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
            WebElement ciudad = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@data-filters-group=\"class\"]//div[@data-filters-item=\"class:class=3\"]//div[@data-testid=\"filters-group-label-content\"]/div)[1]")));
            driver.findElement(By.xpath("//div[@data-filters-item=\"di:di=7583\"]//div[@data-testid=\"filters-group-label-content\"]")).click();


    }
    @Then("the results are displayed")
    public void the_results_are_displayed() {

    }
    @And("the first option is opened")
    public void the_first_option_is_opened() throws InterruptedException {
       driver.findElement (By.xpath("(//a[@data-testid=\"title-link\"])[1]" )).click();
       Thread.sleep(5000);
       driver.close();
       driver.quit();

    }
}
