# prova_4all
Java, Selenium, TestNG

Autor: Eduardo Bastos Brusch
meu git: https://github.com/edubrusch
contato: edubrusch@gmail.com


#Ambiente:
	Java 1.7+
	Maven 3.5+
	TestNG 6.8
	Selenium 3.14

#Modo de utilizar
Testes do TestNG estão sendo executados via maven Surefire.
Foi criado um perfil com as opções padrão: 
	- Browser: chrome
	- path do executavel: pasta Lib

Acessar a pasta do projeto;
Rodar o maven com o comando:  mvn clean test <-P default> <-DOption="valor">

Quaisquer parâmetros -D utilizados sobrepõe os parâmetros do perfil default, caso utilizado. 
Se não for utilizado o perfil, é necessário configurar todos parâmetros.

Parâmetro -P sintaxe -P<perfil>

Parâmetros -D sintaxe: -D<opção>="<valor>"

browser : chrome ou firefox. Padrão é "chrome"
home: homepage a ser carregada. padrão é "https://shopcart-challenge.4all.com/"
console: true ou false. Escreve informações de debug no console. o padrão é true
chrome.path caminho do executável chromedriver. o padrão é "lib/chromedriver.exe"
firefox.path caminho do executável chromedriver. "lib/firefoxdriver.exe"  

Eu removi os arquivos de driver para o projeto ficar mais fácil de ser baixado. por favor encontre os arquivos nos sites:

Endereço do ChromeDriver:
http://chromedriver.chromium.org/

Enredeço do FirefoxDriver:
 https://github.com/mozilla/geckodriver/releases
