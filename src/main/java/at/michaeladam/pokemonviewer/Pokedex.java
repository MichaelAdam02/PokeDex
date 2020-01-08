package at.michaeladam.pokemonviewer;

import at.michaeladam.pokemonviewer.Businesslogic.API_Reader;
import static at.michaeladam.pokemonviewer.Businesslogic.PokeConfig.POKECOUNT;
import at.michaeladam.pokemonviewer.DataLayer.Pokemon;
import java.awt.Image;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Michael ADAM Normal Frame to display the Pokedex"
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
        refresh();
        spOutput.setModel(new SpinnerNumberModel(1, 1, POKECOUNT, 1));
        spOutput.addChangeListener((e) -> {
            id = (int) spOutput.getValue();
            refresh();
        });
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
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                Resize(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(0, 1));

        bilder.setLayout(new java.awt.GridLayout(1, 0));

        lbImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bilder.add(lbImage);

        lbImageBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbImageBack.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bilder.add(lbImageBack);

        getContentPane().add(bilder);

        output.setLayout(new java.awt.GridLayout(1, 0));

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
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            PokemonHolder.getInstance().saveMap();
        } catch (IOException ex) {
        }

    }//GEN-LAST:event_formWindowClosing

    /*
     *When The Shiny State is applied it should refresh the pokemon to display the shiny version of it.
     */
    private void tbShinyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbShinyActionPerformed
        refresh();
    }//GEN-LAST:event_tbShinyActionPerformed
    /*
    * Everytime the window is resized the sprites need to change size, if the
    * size is too small it wont change the size
    *
    */
    private void Resize(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_Resize

        int maxSize = Math.min(lbImage.getWidth(), lbImage.getHeight());
        int sizeDif = Math.abs(Pokemon.getPokesize() - maxSize);
         
        if (sizeDif > 10) {
            Pokemon.setPokesize(maxSize);
        }
        refresh();
    }//GEN-LAST:event_Resize

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
