package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.AdicionarProdutosPage;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web do Modulo de Produtos")
public class ProdutosTest {

    //AQUI EU DECLARO UM ATRIBUTO PARA QUE O METODO NAVEGADOR FORA DE BEFOREEACH VOLTE A FUNCIONAR
    private WebDriver navegador;

    @BeforeEach
    public void beforeEach(){
        //VOU COLOCAR AQUI DENTRO TODO CODIGO QUE FICA ANTES DE CADA TEST
        //Abrir o navegador
        //colocando o This.navegador para usar a classe do WebDriver
        System.setProperty("webdriver.chrome.driver","C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        //Vou maximizar a tela
        this.navegador.manage().window().maximize();

        //Vou definir um tempo de espera padrao de 5 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); //QUANDO A PAGINA CARREGA RAPIDO DEMAIS
        // E NÃO CONSIGO PEGAR O ELEMENTO COM O COMANDO IMPLICITYWAIT ELE FAZ A ESPERA DE X SEGUNDOS ANTES DE FALHAR
        //DANDO TEMPO PARA O ELEMENTO CARREGAR

        //Navegar para a pagina da Lojinha Web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");

    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero() {

        //Fazer login
        //informando o usuário,senha e clicando em entrar em page objects
        String mensagemErroApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()//aqui eu cliquei em entrar depois de preencher os campos
                .adicionarProdutos() //Vou para tela de registro de produto
                .nomeDoProduto("MacBook Pro") //Preenchendo os campo de produto
                .valorDoProduto("000") //Colocando o valor do produto
                .coresDoProduto("Preto, Branco") //Colocando as cores do produto
                .adicionandoProdutoComValorErrado()//Clicando em adicionar produto
                .capturarMensagemApresentada();

        //Vou validar que a mensagem de erro foi apresentada
        // Coloquei a String da mensagem de erro apresentada antes do New LoginPage para validar a mensagem
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemErroApresentada);


    }

    @Test
    @DisplayName("Não é permitido adicionar itens com valor maior que 7.000,00 Mil Reais")
    public void testNaoEPermitidoAdicionarItensComValorMaiorQueSeteMil (){
        String mensagemErroApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()//aqui eu cliquei em entrar depois de preencher os campos
                .adicionarProdutos() //Vou para tela de registro de produto
                .nomeDoProduto("Iphone 15 Pro") //Preenchendo os campo de produto
                .valorDoProduto("700001") //Colocando o valor do produto
                .coresDoProduto("Preto, Branco") //Colocando as cores do produto
                .adicionandoProdutoComValorErrado()//Clicando em adicionar produto
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemErroApresentada);

    }

    @Test
    @DisplayName("Adicionando itens com valores correto")

    public void testAdicionandoItensComValoresCorreto(){
        String mensagemApresentada =  new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()//aqui eu cliquei em entrar depois de preencher os campos
                .adicionarProdutos() //Vou para tela de registro de produto
                .nomeDoProduto("Xbox 2021 Pro") //Preenchendo os campo de produto
                .valorDoProduto("5000") //Colocando o valor do produto
                .coresDoProduto("Preto, Azul") //Colocando as cores do produto
                .adicionandoProdutoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);


    }

    @Test
    @DisplayName("Adicionando um Produto com Valor Maximo de 7.000.00 Mil Reais")

    public void testAdicionandoUmProdutoComValorMaximoDeSeteMilReais(){
        String mensagemApresentada =  new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()//aqui eu cliquei em entrar depois de preencher os campos
                .adicionarProdutos() //Vou para tela de registro de produto
                .nomeDoProduto("Oculos de Sol 2021 Pro") //Preenchendo os campo de produto
                .valorDoProduto("700000") //Colocando o valor do produto
                .coresDoProduto("Preto, Azul") //Colocando as cores do produto
                .adicionandoProdutoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @AfterEach
    public void afterEach(){
        //Fechando o navegador depois de executar todo o teste usando o @AfterEach
       navegador.quit();
    }
}
