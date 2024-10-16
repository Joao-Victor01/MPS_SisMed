package SisMed.menus;

import SisMed.command.AtualizarUsuarioCommand;
import SisMed.command.CadastrarUsuarioCommand;
import SisMed.command.ListarUsuariosCommand;
import SisMed.model.Admin;
import SisMed.model.Medico;
import SisMed.model.Paciente;
import SisMed.service.UsuarioService;
import SisMed.model.Usuario;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuAcoes {
    private UsuarioService usuarioService;
    private Scanner scanner = new Scanner(System.in);

    public MenuAcoes(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void cadastrarUsuario() {
        Usuario usuario = recebeDadosCadastro();

        CadastrarUsuarioCommand cadastrarUsuarioCommand = new CadastrarUsuarioCommand(usuarioService, usuario);
        cadastrarUsuarioCommand.execute();
    }

    public void listarUsuarios() {
        ListarUsuariosCommand listarUsuariosCommand = new ListarUsuariosCommand(usuarioService);
        listarUsuariosCommand.execute();
    }

    public void atualizarUsuario() {
        System.out.println("Escolha uma opção:");
        System.out.println("1. Alterar dados do usuário");
        System.out.println("2. Desfazer última alteração");
        int acao = scanner.nextInt();
        scanner.nextLine();

        switch (acao) {
            case 1:
                Usuario usuario = recebeDadosAtualizacao();
                AtualizarUsuarioCommand atualizarUsuarioCommand = new AtualizarUsuarioCommand(usuarioService, usuario.getUserName(), usuario.getDataNascimento(),
                        usuario.getNome(), usuario.getEndereco(), usuario.getSenha());
                atualizarUsuarioCommand.execute();
                break;
            case 2:
                desfazerUltimaAtualizacao();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private Usuario recebeDadosCadastro() {
        System.out.println("Escolha o tipo de usuário:");
        System.out.println("1. Médico");
        System.out.println("2. Paciente");
        System.out.println("3. Administrador");

        int tipoUsuario = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        Long cpf = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Sexo: ");
        String sexo = scanner.nextLine();

        System.out.print("Data de Nascimento (yyyy-MM-dd): ");
        String dataNascimentoStr = scanner.nextLine();
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr);

        System.out.print("Nome de Usuário: ");
        String userName = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        return criarUsuarioPorTipo(tipoUsuario, nome, cpf, endereco, sexo, dataNascimento, userName, senha);
    }

    private Usuario recebeDadosAtualizacao() {
        System.out.println("Digite o nome de usuário do usuário desejado:");
        String userName = scanner.nextLine();

        Usuario usuario = usuarioService.filtrarUsuarioPorUserName(userName);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return null;
        }

        System.out.println("Nome atual: " + usuario.getNome());
        System.out.print("Novo Nome: ");
        String novoNome = scanner.nextLine();

        System.out.println("Endereço atual: " + usuario.getEndereco());
        System.out.print("Novo Endereço: ");
        String novoEndereco = scanner.nextLine();

        System.out.println("Data de nascimento atual: " + usuario.getDataNascimento());
        System.out.print("Nova Data de Nascimento (yyyy-MM-dd): ");
        String novaDataNascimentoStr = scanner.nextLine();
        LocalDate novaDataNascimento = LocalDate.parse(novaDataNascimentoStr);

        System.out.print("Nova Senha: ");
        String novaSenha = scanner.nextLine();

        usuario.setNome(novoNome);
        usuario.setEndereco(novoEndereco);
        usuario.setDataNascimento(novaDataNascimento);
        usuario.setSenha(novaSenha);

        return usuario;
    }

    // Desfaz a última alteração de um usuário
    private void desfazerUltimaAtualizacao() {
        System.out.print("Digite o nome de usuário para desfazer a última atualização: ");
        String userName = scanner.nextLine();

        Usuario usuario = usuarioService.filtrarUsuarioPorUserName(userName);
        if (usuario != null) {
            usuarioService.desfazerUltimaAtualizacao(usuario);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    // Método para criar o usuário com base no tipo escolhido
    private Usuario criarUsuarioPorTipo(int tipoUsuario, String nome, Long cpf, String endereco, String sexo, LocalDate dataNascimento, String userName, String senha) {
        Usuario usuario = null;

        switch (tipoUsuario) {
            case 1: // Médico
                System.out.print("CRM: ");
                String crm = scanner.nextLine();
                System.out.print("Especializações: ");
                String especializacoes = scanner.nextLine();

                Medico medico = new Medico();
                medico.setNome(nome);
                medico.setCpf(cpf);
                medico.setEndereco(endereco);
                medico.setSexo(sexo);
                medico.setDataNascimento(dataNascimento);
                medico.setUserName(userName);
                medico.setSenha(senha);
                medico.setCrm(crm);
                medico.setEspecializacoes(especializacoes);
                usuario = medico;
                break;
            case 2: // Paciente
                Paciente paciente = new Paciente();
                paciente.setNome(nome);
                paciente.setCpf(cpf);
                paciente.setEndereco(endereco);
                paciente.setSexo(sexo);
                paciente.setDataNascimento(dataNascimento);
                paciente.setUserName(userName);
                paciente.setSenha(senha);
                usuario = paciente;
                break;
            case 3: // Administrador
                Admin admin = new Admin();
                admin.setNome(nome);
                admin.setCpf(cpf);
                admin.setEndereco(endereco);
                admin.setSexo(sexo);
                admin.setDataNascimento(dataNascimento);
                admin.setUserName(userName);
                admin.setSenha(senha);
                usuario = admin;
                break;
            default:
                System.out.println("Tipo de usuário inválido.");
        }

        return usuario;
    }
}
