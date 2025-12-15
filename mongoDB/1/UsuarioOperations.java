import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;


public class UsuarioOperations {

    public static void main(String[] args) {
        // Estabelecer conexão com o MongoDB
        MongoDBConnection connection = new MongoDBConnection();
        MongoDatabase database = connection.getDatabase();

        if (database == null) {
            System.err.println("Não foi possível conectar ao banco de dados.");
            return;
        }

        // Obter a coleção de usuários (será criada automaticamente se não existir)
        MongoCollection<Document> collection = database.getCollection("usuarios");

        System.out.println("=== OPERAÇÕES NO MONGODB ===\n");

        // 1. INSERIR 3 REGISTROS
        System.out.println("1. Inserindo 3 usuários...");
        Usuario alice = new Usuario("Alice", 25);
        Usuario bob = new Usuario("Bob", 30);
        Usuario charlie = new Usuario("Charlie", 35);

        List<Document> usuarios = Arrays.asList(
                alice.toDocument(),
                bob.toDocument(),
                charlie.toDocument()
        );

        collection.insertMany(usuarios);
        System.out.println("Usuários inseridos com sucesso!\n");

        // 2. CONSULTAR OS REGISTROS
        System.out.println("2. Consultando todos os usuários:");
        consultarUsuarios(collection);

        // 3. ALTERAR A IDADE DE BOB PARA 32 ANOS
        System.out.println("\n3. Alterando a idade de Bob para 32 anos...");
        collection.updateOne(eq("nome", "Bob"), set("idade", 32));
        System.out.println("Idade de Bob atualizada com sucesso!\n");

        // 4. CONSULTAR OS REGISTROS NOVAMENTE
        System.out.println("4. Consultando todos os usuários após a atualização:");
        consultarUsuarios(collection);

        // 5. APAGAR O REGISTRO DE CHARLIE
        System.out.println("\n5. Apagando o registro de Charlie...");
        collection.deleteOne(eq("nome", "Charlie"));
        System.out.println("Registro de Charlie apagado com sucesso!\n");

        // 6. CONSULTAR OS REGISTROS FINAIS
        System.out.println("6. Consultando todos os usuários após a exclusão:");
        consultarUsuarios(collection);

        System.out.println("\n=== OPERAÇÕES CONCLUÍDAS ===");

        // Fechar a conexão
        connection.closeConnection();
    }

    /**
     * Método auxiliar para consultar e exibir todos os usuários
     */
    private static void consultarUsuarios(MongoCollection<Document> collection) {
        System.out.println("-------------------------------------------");
        collection.find().forEach(document -> {
            Usuario usuario = Usuario.fromDocument(document);
            System.out.println(usuario);
        });
        System.out.println("-------------------------------------------");
    }
}
