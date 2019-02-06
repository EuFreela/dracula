# Dracula
Projeto Backdoor Dracula
#### v1.1

### BACKDOOR

<p><b>Dracula</b> é uma backdoor para <b>Shell Reverse</b> criada em linguagem java indetectável por antivirus e possui persistencia de conexão - via banco de dados mysql.</p>

<p>Trata-se de uma conexão via socket com shell reverse para sistemas windows. As backdoor comumente são executadas apenas uma vez. Se caso o host não estiver na escuta, a backdoor poderá estar sendo executada na máquina cliente, porém, sem conexão. Por meio do <b>Dracula</b>, uma thread foi implementada. Desta forma a cada 7 segundos a rotina de conexão é chamada permitindo acesso de forma persistente.</p>

<p>O <b>Dracula</b> esta com conexão a um banco de dados remoto. Isso permite várias integrações com o tempo. Atualmente há duas tabelas: reload; server. A tabela reload, permite que a backdoor seja executada remotamente via banco de dados permitindo uma conexão com o cliente a qualquer momento. Já a tabela server permite conexões com o ngrok ou algum servidor remoto - desta forma é possível terminar uma conexão com ngrok e iniciar outra sem a necessidade de criar outra backdoor.</p>
  
<p>Esta aplicação se torna muito eficiente para uso do metasploit, netcat e ngrok para conexões WAN sem necessidade de pagar pelo serviço de ponte de servidores.'Esta backdoor, substitui o uso indireto do msfvenom para criação e backdoors. Nesta versão ainda não é executado o payload, apenas o shell reverse.</p>

<p>Seu uso foi explicado em um tutorial no <a href="https://lameckfernandes.wordpress.com/2019/02/04/dracula-backdoor/" target="_blank">lameckfernandes.wordpress.com</a>.</p>

<hr>

<p>Release em desenvolvimento</p>
<p>Backups com código bateria de testes funcional</p>

<hr>

#### Dracula Dashboard Clean <a href="https://github.com/EuFreela/dracula-dashboard-clean">Link</a>
#### Dracula Plugins: <a href="https://github.com/EuFreela/dracula-hosts">Dracula Hosts</a>
#### Dracula Plugins: <a href="https://github.com/EuFreela/bat-servers">Dracula Bat Servers</a>

<hr>

<img src="https://i.postimg.cc/wjP4MK4P/59f876c83cec115efb36237f.png" />
