package br.com.ntc.crud;

import java.util.Date;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

import br.com.ntc.model.AgendaAcompanhamento;

public class AgendaAcompanhamentoCrud {
	
	public static void main(String[] args) {
		
	Scanner leitor = new Scanner(System.in);
	ArrayList<AgendaAcompanhamento> listaAgendas = new ArrayList<AgendaAcompanhamento>();
	
	carregarDadosDoArquivo(listaAgendas);
	
	int contador = 0;
	int id = obterUltimoId(listaAgendas);
	
	while(contador != 5) {
		System.out.println("-----SISTEMA DE ACOMPANHAMENTO POR AGENDA DE PACIENTE-----");
		System.out.println("Informe ao sistema o que deseja fazer: ");
		System.out.println("1 - Cadastro de Agenda");
		System.out.println("2 - Listagem de Agenda");
		System.out.println("3 - Atualizar Agenda");
		System.out.println("4 - Deletar Agenda");
		System.out.println("5 - Sair");
		contador = leitor.nextInt();
		leitor.nextLine();
		
		 if (contador == 1) {
             System.out.println("---Informe os dados da Agenda---");
             id++;
             
             try {
              	 System.out.println("Medico:");
                 String medico = leitor.next();
            	 
            	 if (validarMedico(medico)) {
            		 System.out.println("Medicamentos(formato medicamento/medicamento):");
		             String medicamentos = leitor.next();


    			        if (validarMedicamentos(medicamentos)) {
    			        	System.out.println("Intervalo em Horas:");
			                int intervalo = leitor.nextInt();

    			            if (validarIntervalo(intervalo)) {
    			            	System.out.println("Data de inicio(formato dd/MM/yyyy):");
			                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			                    Date dataInicio = dateFormat.parse(leitor.next());

    			                if (validarDataInicio(dataInicio)) {
    			                	System.out.println("Data Final(formato dd/MM/yyyy):");
    			                    SimpleDateFormat dateFormate = new SimpleDateFormat("dd/MM/yyyy");
			                         Date dataFim = dateFormate.parse(leitor.next());

    			                    if (validarDataFim(dataFim)) {
    			                    	System.out.println("ID Paciente:");
			                             int paciente = leitor.nextInt();
    			                         
    			                         if(validarPaciente(paciente)) {
    			                             AgendaAcompanhamento novaAgendaAcompanhamento = new AgendaAcompanhamento(id, medico, medicamentos, intervalo, dataInicio, dataFim, paciente);
    			                             listaAgendas.add(novaAgendaAcompanhamento);

    			                        }
    			                    }
    			                }
    			            }
    			        }
    			    }
    			
    		 }
	
              catch (ParseException e) {
                 System.out.println("Formato de data inválido. Por favor, insira no formato dd/MM/yyyy.");
             }
             
             
         }else if(contador == 2) {
			int opcaoListagem = 0;
			System.out.println("1 - Listagem geral de Agendas");
			System.out.println("2 - Listagem por id de Agenda");
			opcaoListagem = leitor.nextInt();
			leitor.nextLine();
			
			if(opcaoListagem == 1) {
				listarTodasAgendas(listaAgendas);
			}else if(opcaoListagem == 2) {
				System.out.println("---Informe o id da Agenda---");
				int idAgenda = leitor.nextInt();
				leitor.nextLine();
				listarAgendaPorId(listaAgendas, idAgenda);
			}
			
		} else if (contador == 3) {
		    System.out.println("---Atualizar Agenda---");
		    System.out.println("Informe o ID da Agenda que deseja atualizar:");
		    int idAgendaAtualizar = leitor.nextInt();
		    leitor.nextLine(); 

		    AgendaAcompanhamento agendaParaAtualizar = encontrarAgendaPorId(listaAgendas, idAgendaAtualizar);

		    if (agendaParaAtualizar != null) {
		        System.out.println("---Informe os novos dados da Agenda---");

		        System.out.println("Medico:");
	            String novoMedico = leitor.next();
	            if (novoMedico.length() <= 50) {
	            	agendaParaAtualizar.setMedico(novoMedico);
	            } else {
	                System.out.println("O Médico deve ter no máximo 50 caracteres. Médico não atualizado.");
	                return;
	            }

	            System.out.println("Medicamentos(formato medicamento/medicamento):");
	            String novosMedicamentos = leitor.next();
	            if (novosMedicamentos.length() <= 100) {
	            	agendaParaAtualizar.setMedicamentos(novosMedicamentos);
	            } else {
	                System.out.println("Os Medicamentos devem ter no máximo 100 caracteres. Medicamentos não atualizados.");
	                return;
	            }

	            System.out.println("Intervalo em Horas:");
	            int novoIntervalo = leitor.nextInt();
	            if (novoIntervalo <= 999) {
	            	agendaParaAtualizar.setIntervalo(novoIntervalo);
	            } else {
	                System.out.println("O Intervalo deve ter no máximo 3 numeros. Intervalol não atualizado.");
	                return;
	            }

	             try {
	            	 
	            	System.out.println("Data de inicio(formato dd/MM/yyyy):");
	            	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	                Date dataInicio = dateFormat.parse(leitor.next());
	                Date dataAtual = new Date(); 
	        	    if (dataInicio.after(dataAtual)) {
	        	       agendaParaAtualizar.setDataInicio(dataInicio);
	        	    } else {
	        	        System.out.println("Erro: A Data de início deve ser posterior à data atual.");
	        	        return;
	        	    }

	        	    System.out.println("Data Final(formato dd/MM/yyyy):");
	                Date dataFim = dateFormat.parse(leitor.next());
	                Date datachuchu = new Date(); 
	        	    if (dataFim.after(dataAtual)) {
		        	    agendaParaAtualizar.setDataFim(dataFim);;
	        	    } else {
	        	        System.out.println("Erro: A Data de início deve ser posterior à data atual.");
	        	        return;
	        	    }
	             
		    
	                 System.out.println("ID "
	                 		+ "Paciente:");
	                 int novoPaciente = leitor.nextInt();
	                 if (novoPaciente <= 99999) {
	 	            	agendaParaAtualizar.setIdPaciente(novoPaciente);
	 	            } else {
	 	                System.out.println("O Intervalo deve ter no máximo 50 caracteres. Intervalol não atualizado.");
	 	                return;
	 	            }
			            
	             } catch (ParseException e) {
	                 System.out.println("Formato de data inválido. Por favor, insira no formato dd/MM/yyyy.");
	             }
	             
	             System.out.println("Agenda atualizado com sucesso!");
	             
		    } else {
		        System.out.println("Agenda com o ID " + idAgendaAtualizar + " não encontrado");
		    }
		}else if (contador == 4) {
		    System.out.println("---Deletar Agenda---");
		    System.out.println("Informe o ID da Agenda que deseja deletar:");
		    int idAgendaDeletar = leitor.nextInt();
		    leitor.nextLine();

		    AgendaAcompanhamento agendaParaDeletar = encontrarAgendaPorId(listaAgendas, idAgendaDeletar);

		    if (agendaParaDeletar != null) {
		    	listaAgendas.remove(agendaParaDeletar);
		        System.out.println("---Agenda deletado com sucesso---");
		    } else {
		        System.out.println("Agenda com o ID " + idAgendaDeletar + " não encontrado");
		    }
		}if(contador == 5) {
			salvarDadosNoArquivo(listaAgendas);
		}
	}
}
	
	private static void salvarDadosNoArquivo(ArrayList<AgendaAcompanhamento> listaAgendas) {
	    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("agenda.txt"))) {
	        Gson gson = new Gson();
	        for (AgendaAcompanhamento agenda : listaAgendas) {
	            String json = gson.toJson(agenda);
	            bufferedWriter.write(json);
	            bufferedWriter.newLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	private static void carregarDadosDoArquivo(ArrayList<AgendaAcompanhamento> listaAgendas) {
	    try (BufferedReader bufferedReader = new BufferedReader(new FileReader("agenda.txt"))) {
	        String linha;
	        Gson gson = new Gson();
	        while ((linha = bufferedReader.readLine()) != null) {
	        	AgendaAcompanhamento agenda = gson.fromJson(linha, AgendaAcompanhamento.class);
	            if (agenda != null) {
	            	listaAgendas.add(agenda);
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("Erro ao carregar dados no arquivo: " + e.getMessage());
	    }
	}
	
	private static int obterUltimoId(ArrayList<AgendaAcompanhamento> listaAgendas) {
    	int ultimoId = 0;
    	for(AgendaAcompanhamento agenda : listaAgendas) {
    		if(agenda.getId() > ultimoId) {
    			ultimoId = agenda.getId();
    		}
    	}
        return ultimoId;
	}
	
	private static AgendaAcompanhamento encontrarAgendaPorId(ArrayList<AgendaAcompanhamento> listaAgendas, int id) {
	    for (AgendaAcompanhamento agenda : listaAgendas) {
	        if (agenda.getId() == id) {
	            return agenda;
	        }
	    }
	    return null;
	}
	
	private static void listarTodasAgendas(ArrayList<AgendaAcompanhamento> listaAgendas) {
        System.out.println("---Lista de Agendas---");
        for (AgendaAcompanhamento agenda : listaAgendas) {
            System.out.println(agenda);
        }
    }
	
	private static void listarAgendaPorId(ArrayList<AgendaAcompanhamento> listaAgendas, int id) {
		System.out.println("---Listagem de Agendas por id---");
		for(AgendaAcompanhamento agenda : listaAgendas) {
			if(agenda.getId() == id) {
				System.out.println(agenda);
				return;
			}
		}
		
		System.out.println("Agenda com o ID " + id + " não encontrado");
	}
	
	private static boolean validarMedico(String medico) {
	    if (medico.length() <= 50) {
	        return true;
	    } else {
	        System.out.println("Erro: O nome do Médico deve ter no máximo 50 caracteres.");
	        return false;
	    }
	}

	private static boolean validarMedicamentos(String medicamentos) {
	    if (medicamentos.length() <= 100) {
	        return true;
	    } else {
	        System.out.println("Erro: Os Medicamentos devem ter no máximo 100 caracteres.");
	        return false;
	    }
	}

	private static boolean validarIntervalo(int intervalo) {
	    if (intervalo <= 999) {
	        return true;
	    } else {
	        System.out.println("Erro: O Intervalo deve ter no máximo 50 caracteres.");
	        return false;
	    }
	}

	private static boolean validarDataInicio(Date dataInicio) {
	    Date dataAtual = new Date(); 
	    if (dataInicio.after(dataAtual)) {
	        return true;
	    } else {
	        System.out.println("Erro: A Data de início deve ser posterior à data atual.");
	        return false;
	    }
	}

	private static boolean validarDataFim(Date dataFim) {
	    Date dataAtual = new Date(); 
	    if (dataFim.after(dataAtual)) {
	        return true;
	    } else {
	        System.out.println("Erro: A Data Final deve ser posterior à data atual.");
	        return false;
	    }
	}


	private static boolean validarPaciente(int paciente) {
	    if (paciente <= 99999) {
	        return true;
	    } else {
	        System.out.println("Erro: O id do paciente deve ter no máximo 5 numeros.");
	        return false;
	    }
	}
}