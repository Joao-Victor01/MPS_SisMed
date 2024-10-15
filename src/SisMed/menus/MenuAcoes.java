package SisMed.menus;

import SisMed.command.CadastrarUsuarioCommand;
import SisMed.command.ListarUsuariosCommand;
import SisMed.controller.UsuarioController;
import SisMed.model.Admin;
import SisMed.model.Medico;
import SisMed.model.Paciente;
import SisMed.model.Usuario;
import SisMed.service.UsuarioService;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuAcoes {
    private UsuarioController usuarioController;
    private UsuarioService usuarioService;
    private Scanner scanner = new Scanner(System.in);

    public MenuAcoes(UsuarioService usuarioService) {
        this.usuarioController = usuarioController;
        this.usuarioService = usuarioService;
    }

    public void cadastrarUsuario() {
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

        Usuario usuario;

        switch (tipoUsuario) {
            case 1: // Médico
                Medico medico = new Medico();
                medico.setNome(nome);
                medico.setCpf(cpf);
                medico.setEndereco(endereco);
                medico.setSexo(sexo);
                medico.setDataNascimento(dataNascimento);
                medico.setUserName(userName);
                medico.setSenha(senha);

                System.out.print("CRM: ");
                String crm = scanner.nextLine();
                medico.setCrm(crm);

                System.out.print("Especializações: ");
                String especializacoes = scanner.nextLine();
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
                return;
        }

        CadastrarUsuarioCommand cadastrarUsuarioCommand = new CadastrarUsuarioCommand(usuarioService, usuario);
        cadastrarUsuarioCommand.execute();
    }

    public void listarUsuarios() {
        ListarUsuariosCommand listarUsuariosCommand = new ListarUsuariosCommand(usuarioService);
        listarUsuariosCommand.execute();
    }
}
