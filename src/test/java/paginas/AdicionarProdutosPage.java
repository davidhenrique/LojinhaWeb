package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdicionarProdutosPage {
    private WebDriver navegador;

    public AdicionarProdutosPage(WebDriver navegador){
        this.navegador = navegador;
    }

    public AdicionarProdutosPage nomeDoProduto(String produtoNome){
        navegador.findElement(By.id("produtonome")).sendKeys(produtoNome);

        return this;
    }

    public AdicionarProdutosPage valorDoProduto(String produtoValor){
        navegador.findElement(By.name("produtovalor")).sendKeys(produtoValor);

        return this;
    }

    public AdicionarProdutosPage coresDoProduto(String produtoCores){
        navegador.findElement(By.id("produtocores")).sendKeys(produtoCores);

        return this;
    }

    public ListaDeProdutosPage adicionandoProdutoComValorErrado(){
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        return new ListaDeProdutosPage (navegador);
    }

    public FormularioDeEdicaoDeProdutoPage adicionandoProdutoComSucesso(){
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        return new FormularioDeEdicaoDeProdutoPage(navegador);
    }
}
