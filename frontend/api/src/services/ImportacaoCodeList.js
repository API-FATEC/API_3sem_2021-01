import { http } from './config'

export default{
	listar:() => {
		return http.get('ImportacaoCodeList')
	},
	editar:(objetoNota) => {

		const data = {
			nota: objetoNota.nota,
			grupo_adm: objetoNota.grupo_adm,
			gerente_proc: objetoNota.gerente_proc,
			status: objetoNota.status,
			tipo_atendimento: objetoNota.tipo_atendimento
		}

		const params = {
			id:objetoNota.id
		}
		return http.put('ImportacaoCodeList',data,{params})
	},
}
