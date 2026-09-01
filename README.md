# Studio Schedule — App Android

Studio Schedule é um marketplace de agendamento com pagamento integrado para
profissionais de beleza e bem-estar: barbearias, personal trainers, estúdios
de pilates, manicures, podólogas, massagistas, trancistas e autônomos do
setor. Este repositório contém o **app Android nativo**, com dois perfis sob
um único login: **Empreendedor** (dono do estabelecimento) e **Cliente**
(consumidor final).

> Esta etapa entrega apenas o **scaffold** do app: navegação, telas e dados
> mockados. Não há backend, autenticação real, banco de dados ou pagamento
> integrado ainda — isso será um projeto separado.

## Stack

- **Kotlin** + **Jetpack Compose** (UI declarativa) + **Material 3**
- **Navigation Compose** para navegação entre telas
- Arquitetura **MVVM**, organizada em `ui/`, `domain/` e `data/`
- **Gradle Kotlin DSL** (`build.gradle.kts`) com **version catalog**
  (`gradle/libs.versions.toml`) centralizando as versões de dependências
- `minSdk 26`, `targetSdk`/`compileSdk 35`

## Estrutura do projeto

```
app/src/main/java/com/colossalgrupo/studioflow/
├── domain/
│   ├── model/        # Professional, Establishment, SubscriptionPlan, etc.
│   └── repository/   # Interfaces de repositório (contrato para o futuro backend)
├── data/
│   ├── mock/         # Dados mock (estabelecimentos, profissionais, horários)
│   └── repository/   # Implementações em memória dos repositórios
└── ui/
    ├── theme/         # Cores, tipografia e ColorScheme (light/dark)
    ├── navigation/    # Grafo de navegação (NavHost) e rotas
    ├── splash/        # Splash screen
    ├── auth/          # Login (estado local, sem autenticação real)
    ├── profileselect/ # Seleção de perfil: Empreendedor ou Cliente
    ├── client/         # Home do cliente (lista de estabelecimentos + agendar)
    ├── entrepreneur/   # Home do empreendedor + cadastro de profissional
    └── components/     # Composables reutilizáveis
```

Cada tela segue o padrão `Screen` (Composable) + `ViewModel` (estado e
regras), com os repositórios em memória injetados por parâmetro padrão —
prontos para serem trocados por implementações reais que conversem com uma
API quando o backend existir.

## Tema visual

Paleta baseada no plano de produto do Studio Schedule (Material 3 `ColorScheme`,
com suporte a light e dark):

| Papel                        | Light     | Dark      |
|-------------------------------|-----------|-----------|
| Accent ("salon green")        | `#0F6B5C` | `#48C7A6` |
| Gold (destaque plano Diamond)  | `#B8863A` | `#D9A857` |
| Rose (tag de categoria)        | `#B15A46` | `#D98A73` |
| Fundo                          | `#F5F6F3` | `#101513` |
| Superfície                     | `#FFFFFF` | `#161D1A` |

Tipografia usa a escala padrão do Material 3 com a fonte do sistema
(Roboto).

## Telas iniciais

1. **Splash** — logo/nome do app.
2. **Login** — e-mail/senha em estado local (sem autenticação real ainda).
3. **Seleção de perfil** — Empreendedor ou Cliente, no mesmo login.
4. **Home do Cliente** — lista mock de estabelecimentos/profissionais, com
   botão "Agendar".
5. **Home do Empreendedor** — lista mock de profissionais cadastrados, plano
   de assinatura atual e botão "novo profissional".
6. **Novo profissional** — formulário (nome, CPF, especialidades, conta
   bancária/Pix, percentual de repasse, periodicidade), salvo em memória.

## Como rodar

1. Abra a pasta do projeto no **Android Studio** (versão recente, com AGP
   compatível — veja `gradle/libs.versions.toml`).
2. Deixe o Android Studio sincronizar o Gradle e baixar o SDK necessário
   (`compileSdk 35`).
3. Rode o app em um emulador ou dispositivo físico (`minSdk 26`).

Ou via linha de comando, com o Android SDK configurado:

```bash
./gradlew assembleDebug
```

## Roadmap (fora do escopo desta etapa)

- Backend próprio (outro repositório) com API para autenticação, agenda,
  catálogo de serviços e pagamentos.
- Pagamento integrado via Pix e cartão, com **split de pagamento** automático
  entre plataforma e profissional, conforme o plano de assinatura:
  - **Standard** — R$ 49,90/mês, 5% por transação, até 3 profissionais.
  - **Black** — R$ 89,90/mês, 2,5% por transação, até 10 profissionais.
  - **Diamond** — R$ 189,90/mês, 1,5% por transação, profissionais ilimitados.
- Repasse automático ao profissional (semanal ou mensal, conforme regra
  cadastrada) e painel financeiro com extrato real.
- Autenticação real e persistência de dados (banco de dados/API).
- Busca por localização e avaliações reais de clientes.
