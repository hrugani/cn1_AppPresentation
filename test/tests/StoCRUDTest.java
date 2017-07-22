package tests;

import com.codename1.testing.AbstractTest;
import com.mycompany.myapp.Client;
import com.mycompany.myapp.PersistenceInterface;
import com.mycompany.myapp.PersistenceSto;

/**
 *
 * @author hrugani
 * 
 * This test will Create/Read/Update/Delete
 * 1 record that has data about 1 Client.
 * It will use persistence in "Codename One Local Storage".         
 * It will execute each action and after that it verifies        
 * whether respective action was executed correctly. 
 * 
 */
public class StoCRUDTest extends AbstractTest {
    
    final int TWO_MINUTES = 120000; 
    PersistenceInterface p;
    
    
    @Override
    public int getTimeoutMillis() {
        return TWO_MINUTES;
    }
    
    /**
     *
     */
    public void cleanUp() {           
    }
    
    @Override
    public void prepare() {
        p = PersistenceSto.getInstance();
    }
    
    @Override
    public boolean shouldExecuteOnEDT() {
        return false;
    }
    
    
    @Override
    public boolean runTest() throws Exception {
        
        ////////////////////////
        // Create Client Test
        ////////////////////////
        
        int clientId = p.getNextId();
        Client c = new Client(
            clientId,
            "Cliente " + clientId,
            30 + clientId,
            Client.GEO_IBM
        );

        Client cCreated = p.addClient(c);
        assertNotNull(
            cCreated,
            new StringBuilder()
                .append("Create Client[").append("[")
                .append(cCreated.toString())
                .append("]")
                .append(" int Codename One Storage failed.")
                .toString()
        );
        
        log(
            new StringBuilder()
            .append("Create action succesful.\n")
            .append("") 
            .toString()
        );

        //////////////////////
        // Read client Test
        //////////////////////
        
        Client cRead = p.getClient(clientId);
        
        assertNotNull(
            cRead,
            new StringBuilder()
                .append("Reading Client[").append("[")
                .append(cRead.toString())
                .append("]")
                .append(" from Codename One Storage failed.")
                .toString()
        );
        
        assertEqual(cRead, cCreated);
        
        log(
            new StringBuilder()
            .append("Read action successful.")
            .append("") 
            .toString()
        );
        
        ///////////////////////
        // Update Client test
        ///////////////////////
        
        cRead.setName(cRead.getName() + " Updated");
        cRead.setAge(57);
        cRead.setGeoLocation(Client.GEO_HOME);
        
        Client cUpdated = p.updateClient(cRead);
        
        assertNotNull(
            cUpdated,
            new StringBuilder()
                .append("Update Client[").append("[")
                .append(cUpdated.toString())
                .append("]")
                .append(" in Codename One Storage failed.")
                .toString()
        );
        
        assertEqual(cUpdated, cRead);
        
        log(
            new StringBuilder()
            .append("Update action successful.")
            .append("") 
            .toString()
        );
        
        //////////////////////
        // Delete Client test
        //////////////////////
        
        p.removeClient(cUpdated);
        
        Client dClient = p.getClient(cUpdated.getId());
        
        assertNull(dClient);
        
        log(
            new StringBuilder()
            .append("Delete action successful.\n")
            .append("") 
            .toString()
        );
        
        return true;
        
    }
    
}
