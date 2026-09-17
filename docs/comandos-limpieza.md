Windows (PowerShell):

# Verifica que la capa domain no importe clases de la infraestructura.
# El dominio debe ser independiente de la infraestructura en arquitectura hexagonal.
Select-String -Path "src\main\java\com\blue\bank\blue_bank\domain\**\*.java" -Pattern "import com.blue.bank.blue_bank.infrastructure"

# Verifica que la capa domain no utilice JPA (jakarta.persistence).
# El dominio no debe depender de la tecnología de persistencia.
Select-String -Path "src\main\java\com\blue\bank\blue_bank\domain\**\*.java" -Pattern "import jakarta.persistence"

# Verifica que la capa domain no dependa de Spring.
# El dominio debe contener únicamente la lógica de negocio.
Select-String -Path "src\main\java\com\blue\bank\blue_bank\domain\**\*.java" -Pattern "import org.springframework"

# Verifica que la capa application no importe clases de la infraestructura.
# La aplicación debe depender de puertos o interfaces, no de implementaciones concretas.
Select-String -Path "src\main\java\com\blue\bank\blue_bank\application\**\*.java" -Pattern "import com.blue.bank.blue_bank.infrastructure"


Mac y Linux (Terminal):

grep -r "import com.blue.bank.blue_bank.infrastructure" src/main/java/com/blue/bank/blue_bank/domain/
grep -r "import jakarta.persistence" src/main/java/com/blue/bank/blue_bank/domain/
grep -r "import org.springframework" src/main/java/com/blue/bank/blue_bank/domain/
grep -r "import com.blue.bank.blue_bank.infrastructure" src/main/java/com/blue/bank/blue_bank/application/


IntelliJ (cualquier sistema operativo):

Ctrl+Shift+F (o Cmd+Shift+F en Mac) → Find in Files.

En "Scope" seleccioná el paquete `domain/` y buscá:
- import com.blue.bank.blue_bank.infrastructure
- import jakarta.persistence
- import org.springframework

Luego seleccioná el paquete `application/` y buscá:
- import com.blue.bank.blue_bank.infrastructure

Los cuatro controles tienen que dar vacío. Si alguno devuelve resultados, significa que existe un import que viola la arquitectura hexagonal y debe corregirse.
