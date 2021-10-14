package modulos.produtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("Testes Web do Modulo de Produtos")
public class ProdutosTest {
    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero() {
        //LISTANDO PASSO A PASSO DO TESTE
        //Abrir o navegador
        System.setProperty("webdriver.chrome.driver","C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();

        //Vou maximizar a tela
        navegador.manage().window().maximize();

        //Vou definir um tempo de espera padrao de 5 segundos
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); //QUANDO A PAGINA CARREGA RAPIDO DEMAIS
        // E NÃO CONSIGO PEGAR O ELEMENTO COM O COMANDO IMPLICITYWAIT ELE FAZ A ESPERA DE X SEGUNDOS ANTES DE FALHAR
        //DANDO TEMPO PARA O ELEMENTO CARREGAR

        //Navegar para a pagina da Lojinha Web
        navegador.get("http://165.227.93.41/lojinha-web/v2/");

        //Fazer login
        // navegador.findElement(By.cssSelector("label[for='usuario']")).click(); Usei o CssSelector e usar quando necessario o click
        navegador.findElement(By.id("usuario")).sendKeys("admin"); //Usei o ID

        //navegador.findElement(By.cssSelector("label[for='senha']")).click(); Usei o CssSelector e usar o click quando necessario
        navegador.findElement(By.id("senha")).sendKeys("admin"); //Usei o ID e SendKeys para digital no campo

        navegador.findElement(By.cssSelector("button[type='submit']")).click(); //CLICANDO NO BOTÃO ENTRAR


        //Vou para tela de registro de produto
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();//CLICANDO NO LINK EM FORMA DE TEXTO

        //Vou preencher dados do produto e o valor sera igual a zero
        navegador.findElement(By.id("produtonome")).sendKeys("Macbook Pro");
        navegador.findElement(By.name("produtovalor")).sendKeys("000");
        navegador.findElement(By.id("produtocores")).sendKeys("preto,branco");

        //Vou submeter o formulario
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        //Vou validar que a mensagem de erro foi apresentada
        String mensagemToast = navegador.findElement(By.cssSelector(".toast.rounded")).getText();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemToast);

        //NESSA STEP DE CIMA EU PEGUEI A MENSAGEM NO POPUP TOAST CRIEI UMA VARIÁVEL MENSAGEMTOAST
        //PARA VALIDAR EU FIZ UMA ASSERTIONS COM A MENSAGEM QUE APARECE NO POPUP E DEPOIS QUE
        // COLOCAR A MENSAGEM TODA COLOCAR UMA VIRGULA E COLOCAR A VARIÁVEL LOGO DEPOIS DA STRING

        //Fechando o navegador depois do teste executado
        navegador.quit();
    }
}
