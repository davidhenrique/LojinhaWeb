package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListaDeProdutosPage {
    private WebDriver navegador;

    public ListaDeProdutosPage(WebDriver navegador){
        this.navegador = navegador;
    }

    public AdicionarProdutosPage adicionarProdutos(){
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();//CLICANDO NO LINK EM FORMA DE TEXTO

        return new AdicionarProdutosPage(navegador);
    }

    public String capturarMensagemApresentada(){
        return navegador.findElement(By.cssSelector(".toast.rounded")).getText();
    }

}
