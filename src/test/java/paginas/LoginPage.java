package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver navegador;

    public LoginPage(WebDriver navegador){
        this.navegador = navegador;
    }

    public LoginPage informarOUsuario(String usuario) {
        navegador.findElement(By.cssSelector("label[for='usuario']")).click(); //Usei o CssSelector e usar quando necessario o click
        navegador.findElement(By.id("usuario")).sendKeys(usuario); //Usei o ID

        return this;
    }

    public LoginPage informarASenha(String senha){
        navegador.findElement(By.cssSelector("label[for='senha']")).click(); //Usei o CssSelector e usar o click quando necessario
        navegador.findElement(By.id("senha")).sendKeys(senha); //Usei o ID e SendKeys para digital no campo

        return this;
    }

    public ListaDeProdutosPage submeterFormularioDeLogin(){
        navegador.findElement(By.cssSelector("button[type='submit']")).click(); //CLICANDO NO BOTÃO ENTRAR

        return new ListaDeProdutosPage(navegador);
    }
}
