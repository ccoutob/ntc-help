package br.com.ntc.crud;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

import br.com.ntc.model.Paciente;

public class PacienteCrud {
	
	public static void main(String[] args) {
		
	Scanner leitor = new Scanner(System.in);
	ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();
	
	carregarDadosDoArquivo(listaPacientes);
	
	int contador = 0;
	int id = obterUltimoId(listaPacientes);
	
	while(contador != 5) {
		System.out.println("-----SISTMA DE CADASTRO DE PACIENTE-----");
		System.out.println("1 - Cadastro de Paciente");
		System.out.println("2 - Listagem de Paciente");
		System.out.println("3 - Atualizar Paciente");
		System.out.println("4 - Deletar Paciente");
		System.out.println("5 - Sair");
		contador = leitor.nextInt();
		leitor.nextLine();
		
		if(contador == 1) {
			System.out.println("---Informe os dados do Paciente---");
			id++;
			
            System.out.println("Nome:");
			String nome = leitor.next();
       	 
       	 if (validarNome(nome)) {
       		System.out.println("Idade");
			int idade = leitor.nextInt();

			        if (validarIdade(idade)) {
			        	System.out.println("Cpf(formato xxx.xxx.xxx-xx):");
				        String cpf = leitor.next();

			            if (validarCpf(cpf)) {
			            	System.out.println("Email:");
			    	        String email = leitor.next();

			                if (validarEmail(email)) {
			                	System.out.println("Telefone:");
			        	        String telefone = leitor.next();

			                    if (validarTelefone(telefone)) {
			                    	Paciente novoPaciente = new Paciente(id, nome, idade, cpf, email, telefone);
			                        listaPacientes.add(novoPaciente);
			              }
			          }
			      }
			  }
       	 }     
		}else if(contador == 2) {
			int opcaoListagem = 0;
			System.out.println("1 - Listagem geral de Pacientes");
			System.out.println("2 - Listagem por id de Paciente");
			opcaoListagem = leitor.nextInt();
			leitor.nextLine();
			
			if(opcaoListagem == 1) {
				listarTodosPacientes(listaPacientes);
			}else if(opcaoListagem == 2) {
				System.out.println("---Informe o id do Paciente---");
				int idPaciente = leitor.nextInt();
				leitor.nextLine();
				listarPacientePorId(listaPacientes, idPaciente);
			}
			
		} else if(contador == 3) {
			System.out.println("---Atualizar Paciente---");
		    System.out.println("Informe o ID do Paciente que deseja atualizar:");
		    int idPacienteAtualizar = leitor.nextInt();
		    leitor.nextLine(); 

		    Paciente pacienteParaAtualizar = encontrarPacientePorId(listaPacientes, idPacienteAtualizar);

		    if (pacienteParaAtualizar != null) {
		        System.out.println("---Informe os novos dados do Paciente---");

		        System.out.println("Nome:");
	            String novoNome = leitor.next();
	            if (novoNome.length() <= 50) {
	            	pacienteParaAtualizar.setNome(novoNome);
	            } else {
	                System.out.println("O nome do Paciente deve ter no máximo 50 caracteres. Paciente não atualizado.");
	                return;
	            }

	            System.out.println("Idade:");
	            int novaIdade = leitor.nextInt();
	            if (novaIdade <= 999) {
	            	pacienteParaAtualizar.setIdade(novaIdade);
	            } else {
	                System.out.println("A idade deve ter no máximo 3 caracteres. Idade não atualizada.");
	                return;
	            }

	            System.out.println("Cpf:");
	            String novoCpf = leitor.next();
	            if (novoCpf.length() <= 14) {
	            	pacienteParaAtualizar.setCpf(novoCpf);
	            } else {
	                System.out.println("O Cpf deve ter no máximo 14 numeros. Cpf não atualizado.");
	                return;
	            }

	            System.out.println("Email:");
	            String novoEmail = leitor.next();
	            if (novoEmail.length() <= 100) {
	            	pacienteParaAtualizar.setEmail(novoEmail);
	            } else {
	                System.out.println("O Email deve ter no máximo 100 caracteres. Email não atualizado.");
	                return;
	            }
	            
	            System.out.println("Telefone:");
	            String novoTelefone = leitor.next();
	            if (novoTelefone.length() <= 15) {
	            	pacienteParaAtualizar.setTelefone(novoTelefone);
	            } else {
	                System.out.println("O Telefone deve ter no máximo 15 numeros. Telefone não atualizado.");
	                return;
	            }
	             
	             System.out.println("Paciente atualizado com sucesso!");
	             
		    } else {
		        System.out.println("Paciente com o ID " + idPacienteAtualizar + " não encontrado");
		    }
		}else if(contador == 4) {
			System.out.println("---Deletar Paciente---");
		    System.out.println("Informe o ID do Paciente que deseja deletar:");
		    int idPacienteDeletar = leitor.nextInt();
		    leitor.nextLine();

		    Paciente pacienteParaDeletar = encontrarPacientePorId(listaPacientes, idPacienteDeletar);

		    if (pacienteParaDeletar != null) {
		    	listaPacientes.remove(pacienteParaDeletar);
		        System.out.println("---Paciente deletado com sucesso---");
		    } else {
		        System.out.println("Paciente com o ID " + idPacienteDeletar + " não encontrado");
		    }
		}if(contador == 5) {
			salvarDadosNoArquivo(listaPacientes);
		}
	}
}
	
	private static void salvarDadosNoArquivo(ArrayList<Paciente> listaPacientes) {
	    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("pacientes.txt"))) {
	        Gson gson = new Gson();
	        for (Paciente paciente : listaPacientes) {
	            String json = gson.toJson(paciente);
	            bufferedWriter.write(json);
	            bufferedWriter.newLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	private static void carregarDadosDoArquivo(ArrayList<Paciente> listaPacientes) {
	    try (BufferedReader bufferedReader = new BufferedReader(new FileReader("pacientes.txt"))) {
	        String linha;
	        Gson gson = new Gson();
	        while ((linha = bufferedReader.readLine()) != null) {
	        	Paciente paciente = gson.fromJson(linha, Paciente.class);
	            if (paciente != null) {
	            	listaPacientes.add(paciente);
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("Erro ao carregar dados no arquivo: " + e.getMessage());
	    }
	}
	
	private static int obterUltimoId(ArrayList<Paciente> listaPacientes) {
    	int ultimoId = 0;
    	for(Paciente paciente : listaPacientes) {
    		if(paciente.getId() > ultimoId) {
    			ultimoId = paciente.getId();
    		}
    	}
        return ultimoId;
	}
	
	private static Paciente encontrarPacientePorId(ArrayList<Paciente> listaPacientes, int id) {
	    for (Paciente paciente : listaPacientes) {
	        if (paciente.getId() == id) {
	            return paciente;
	        }
	    }
	    return null;
	}
	
	private static void listarTodosPacientes(ArrayList<Paciente> listaPacientes) {
        System.out.println("---Lista de Pacientes---");
        for (Paciente paciente : listaPacientes) {
            System.out.println(paciente);
        }
    }
	
	private static void listarPacientePorId(ArrayList<Paciente> listaPacientes, int id) {
		System.out.println("---Listagem de Paciente por id---");
		for(Paciente paciente : listaPacientes) {
			if(paciente.getId() == id) {
				System.out.println(paciente);
				return;
			}
		}
		
		System.out.println("Paciente com o ID " + id + " não encontrado");
	}
	
	private static boolean validarNome(String nome) {
	    if (nome.length() <= 50) {
	        return true;
	    } else {
	        System.out.println("Erro: O nome do Paciente deve ter no máximo 50 caracteres.");
	        return false;
	    }
	}

	private static boolean validarIdade(int idade) {
	    if (idade <= 999) {
	        return true;
	    } else {
	        System.out.println("Erro: A idade deve ter no máximo 3 digitos.");
	        return false;
	    }
	}

	private static boolean validarCpf(String cpf) {
	    if (cpf.length() <= 14) {
	        return true;
	    } else {
	        System.out.println("Erro: O Cpf deve ter no máximo 14 caracteres.");
	        return false;
	    }
	}
	
	private static boolean validarEmail(String email) {
	    if (email.length() <= 100) {
	        return true;
	    } else {
	        System.out.println("Erro: O email deve ter no máximo 100 caracteres.");
	        return false;
	    }
	}

	private static boolean validarTelefone(String telefone) {
	    if (telefone.length() <= 15) {
	        return true;
	    } else {
	        System.out.println("Erro: O Telefone devem ter no máximo 15 caracteres.");
	        return false;
	    }
	}
}
