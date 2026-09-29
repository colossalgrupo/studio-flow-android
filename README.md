# Studio Schedule — App Android

Studio Schedule é um marketplace de agendamento com pagamento integrado para
profissionais de beleza e bem-estar: barbearias, personal trainers, estúdios
de pilates, manicures, podólogas, massagistas, trancistas e autônomos do
setor. Este repositório contém o **app Android nativo do cliente final** —
quem busca um estabelecimento, agenda um horário e paga. O dono do
estabelecimento (perfil Empreendedor) usa o painel web, em outro repositório
(`studio-flow-web`), não este app.

> Login e autenticação já conversam com o backend real (`studio-flow-backend`).
> O restante do fluxo (busca de estabelecimento, agendamento e pagamento) ainda
> usa dados mockados — ver o roadmap abaixo.

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
│   ├── model/        # Establishment, AuthSession, etc.
│   └── repository/   # Interfaces de repositório
├── data/
│   ├── remote/        # Retrofit/OkHttp, DTOs, interceptor de autenticação
│   ├── local/          # Sessão autenticada (token) cifrada no device
│   ├── mock/           # Dados mock (estabelecimentos, horários) — só o que
│   │                    ainda não tem endpoint real
│   └── repository/     # Implementações (RemoteAuthRepository já é real;
│                          o resto ainda é em memória)
└── ui/
    ├── theme/         # Cores, tipografia e ColorScheme (light/dark)
    ├── navigation/    # Grafo de navegação (NavHost) e rotas
    ├── splash/        # Splash screen
    ├── auth/          # Login (autenticação real via backend)
    ├── client/         # Home do cliente (lista de estabelecimentos + agendar)
    └── components/     # Composables reutilizáveis
```

Cada tela segue o padrão `Screen` (Composable) + `ViewModel` (estado e
regras). `LoginViewModel` já recebe suas dependências via `AppContainer`
(`StudioScheduleApplication`); as telas que ainda usam dados mockados seguem
recebendo o repositório em memória por parâmetro padrão, até ganharem um
endpoint real.

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
2. **Login** — e-mail/senha, autentica contra `POST /api/auth/login` do
   backend. Uma conta de Empreendedor autentica normalmente mas é rejeitada
   aqui (esse perfil não tem o que fazer neste app).
3. **Home do Cliente** — lista mock de estabelecimentos/profissionais, com
   botão "Agendar" (fluxo de agendamento e pagamento ainda por vir).

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

- Repositório de estabelecimentos via API real (`GET /api/estabelecimentos`),
  no lugar do mock.
- Fluxo de agendamento: seleção de serviço/horário e criação do agendamento
  via API.
- Checkout: tela de pagamento (Pix/cartão), campo de CPF (exigido pelo
  gateway Asaas) e exibição do QR code Pix quando o pagamento ficar pendente.
- Busca por localização e avaliações reais de clientes.
- Split de pagamento entre plataforma/estabelecimento/profissional e planos
  de assinatura (Standard/Black/Diamond) são regras do backend e do painel
  web do Empreendedor — não deste app.
