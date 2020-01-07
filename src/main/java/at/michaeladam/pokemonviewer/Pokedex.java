package at.michaeladam.pokemonviewer;

import at.michaeladam.pokemonviewer.Businesslogic.API_Reader;
import at.michaeladam.pokemonviewer.DataLayer.Pokemon;
import static java.lang.Thread.sleep;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Michael ADAM
 * Normal Frame to display the Pokedex"
 */
public class Pokedex extends javax.swing.JFrame {

    
    
    private int id = 1;
    private final API_Reader apr = new API_Reader();
    private Pokemon current;
    
    /**
     * Creates the Pokedex-frame
     */ 
    public Pokedex() {
        initComponents();

    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bilder = new javax.swing.JPanel();
        lbImage = new javax.swing.JLabel();
        lbImageBack = new javax.swing.JLabel();
        output = new javax.swing.JPanel();
        lbTypes = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        spOutput = new javax.swing.JSpinner();
        tbShiny = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 400));
        setName("Pokedex"); // NOI18N
        setPreferredSize(new java.awt.Dimension(700, 400));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(0, 1));

        bilder.setLayout(new java.awt.GridLayout());

        lbImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bilder.add(lbImage);

        lbImageBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbImageBack.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bilder.add(lbImageBack);

        getContentPane().add(bilder);

        output.setLayout(new java.awt.GridLayout());

        lbTypes.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        lbTypes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTypes.setText("Types");
        output.add(lbTypes);

        lbName.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        lbName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbName.setText("Name");
        output.add(lbName);

        spOutput.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        spOutput.setMinimumSize(new java.awt.Dimension(120, 26));
        spOutput.setName(""); // NOI18N
        spOutput.setPreferredSize(new java.awt.Dimension(60, 26));
        spOutput.setValue(1);
        output.add(spOutput);

        tbShiny.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        tbShiny.setText("Shiny");
        tbShiny.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbShinyActionPerformed(evt);
            }
        });
        output.add(tbShiny);

        getContentPane().add(output);

        pack();
		refresh();
        spOutput.addChangeListener((ChangeEvent e) -> {
            id = (int) spOutput.getValue();
            refresh();
        });
		
    }// </editor-fold>//GEN-END:initComponents

    /*
    *   TODO: Implement saving of the pokemonHolders
    */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        PokemonHolder.getInstance().saveMap();
    }//GEN-LAST:event_formWindowClosing

    /*
    *When The Shiny State is applied it should refresh the pokemon to display the shiny version of it.
    */
    private void tbShinyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbShinyActionPerformed
        refresh();
    }//GEN-LAST:event_tbShinyActionPerformed

    /*
        Refreshes the icons names and type text
    */    
    private void refresh() {
        current = PokemonHolder.getInstance().getPokemon(id);

        if (tbShiny.isSelected()) {
            lbImage.setIcon(new ImageIcon(current.getFront_shiny()));
            lbImageBack.setIcon(new ImageIcon(current.getBack_shiny()));
        } else {
            lbImage.setIcon(new ImageIcon(current.getFront_default()));
            lbImageBack.setIcon(new ImageIcon(current.getBack_default()));
        }
        lbTypes.setText(current.getType());
        lbName.setText(current.name);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bilder;
    private javax.swing.JLabel lbImage;
    private javax.swing.JLabel lbImageBack;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbTypes;
    private javax.swing.JPanel output;
    private javax.swing.JSpinner spOutput;
    private javax.swing.JToggleButton tbShiny;
    // End of variables declaration//GEN-END:variables

     
}
