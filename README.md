# TaskBotTelegram
🚀 Tech Stack

    Java 17+

    Spring Boot

    TelegramBots Java API

    Maven

⚙️ Features

    /start — greets the user.

    📋 My Tasks — view the list of added tasks.

    📝 Create Task — add a new task.

    ❌ Delete Task — delete a task by ID.

    🛟 Support — contact button for admin support.

🏗️ Architecture

    commandRecive — processing the start command.

    config — bot token and name configuration.

    menu — user interface menu with buttons.

    repository — task storage in memory (InMemoryTaskRepo).

    service — business logic for tasks and user states.

    state — tracking user states (BotState).

    task — task model (Task).

🛠️ Installation and Setup

    Clone the repository:

git clone https://github.com/your-link/taskbot.git
cd taskbot

    Configure your application.properties file:

spring.application.name=taskBot
bot.name=TaskBot
bot.token=YOUR_BOT_TOKEN_FROM_BOTFATHER

    Build and run the project:

./mvnw spring-boot:run

Or run it from your IDE (e.g., IntelliJ IDEA) by executing TaskBotApplication.java.

    Register the bot on Telegram via @BotFather.
