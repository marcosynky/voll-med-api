package med.voll.api.domain.medico.dto;

import med.voll.api.domain.medico.Especialidade;  // Importa a enum de especialidades médicas
import med.voll.api.domain.medico.Medico;  // Importa a entidade Medico

// A classe 'DadosListagemMedico' é um DTO usado para listar médicos com as informações básicas
public record DadosListagemMedico(
        Long id,  // ID do médico
        Boolean ativo,  // Status de ativo ou inativo do médico
        String nome,  // Nome do médico
        String email,  // E-mail do médico
        String crm,  // CRM do médico (registro profissional)
        Especialidade especialidade  // Especialidade médica do médico
) {

    // Construtor que transforma um objeto Medico em DadosListagemMedico
    public DadosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getAtivo(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
