package Clock;
import bgi.*;
import java.awt.Color;
import javax.swing.text.*;
import java.util.ArrayList;

/**
 * Single digit display for use in ClockFrame or TestClockDigit
 * @author 
 */
public class Digit extends javax.swing.JLayeredPane implements TouchDigit {
 

    public final int LEFT = 0;
    public final int CENTRE = 1;
    public final int RIGHT = 2;
    
    /**
     * Creates new Digit. Also initializes array of listeners.
     */
    public Digit() {
        initComponents();
        listeners = new ArrayList();
        clearText();
    }

     /**
     * Displays the Integer passed within the digit label
     * @param i – the integer to be displayed, 0-9 inclusive.
     */
    @Override
    public void setDigit(int i){
        this.digitLabel.setText(String.valueOf(i % 10));
    }

    /**
     * Gets the digit displayed within digit label.
     * @return the integer value currently displayed, -1 if not integer
     */
    @Override
    public int getDigit(){
        char currentChar = this.digitLabel.getText().charAt(0);
        if(Character.isDigit(currentChar)){
            return Character.getNumericValue(currentChar);
        } else return -1;
    }

     /**
     * For each element in array, set the textSpace display text to the corresponding string.
     * @param text - String array containing text overlays
     */    
    @Override
    public void setText(String[] text){
        textSpace.setText("");
        SimpleAttributeSet alignment = new SimpleAttributeSet();
        StyleConstants.setAlignment(alignment, textAlignment);
        String arr = textSpace.getText();
        for(int i = text.length-1; i >= 0; i--) {
            // the Document.insertString method requires exception handling.
            try {
                if(i != text.length-1) {     // no newLine at end
                    textSpace.getDocument().insertString(0,  "\n", alignment);
                }
                textSpace.getDocument().insertString(0, text[i], alignment);
                arr = textSpace.getText();
            } catch(BadLocationException e){
                System.out.println("Invalid index for insertString()");
            }
        }
    }
    
     /**
     * @return list of strings currently displayed
     */
    @Override
    public String[] getText(){
        String[] lines = new String[rows];
        String[] currentText = textSpace.getText().split("\n");
        for(int i = 0; i < currentText.length; i++){
            lines[i] = currentText[i];
        }
        return lines;
    }
    
     /**
     * Set the horizontal text alignment of textSpace.
     * @param i - defined Integers 0: LEFT, 1: CENTRE, 2: RIGHT
     */
    @Override
    public void setTextAlignment(int i){
        textAlignment = i;
        StyledDocument doc = textSpace.getStyledDocument();
        SimpleAttributeSet alignment = new SimpleAttributeSet();
        StyleConstants.setAlignment(alignment, i);
        doc.setParagraphAttributes(0, doc.getLength(), alignment, false);
    }

     /**
     * Get the horizontal text alignment.
     * @return defined Integers 1: LEFT, 2: CENTRE, 3: RIGHT
     */    
    @Override
    public int getTextAlignment(){
        return textAlignment;
    }
    
    /**
     * register parent component as a TouchListener
     */  
    @Override
    public synchronized void addTouchListener(TouchListener touchListener) {
        listeners.add(touchListener);
    }

    /**
     * deregister parent component as a TouchListener
     */ 
    @Override
    public synchronized void removeTouchListener(TouchListener touchListener) {
        listeners.remove(touchListener);
    }

    /// End of methods required by TouchDigit interface
    
    /**
     * Sets the text of textSpace corresponding to the index.
     * @param text - String to replace textSpace row with
     * @param index - integer row of textSpace to change (0-10)
     */
    public void setText(int index, String text){
        String[] lines = this.getText();
        lines[index] = text;
        this.setText(lines);
    }
    
    /**
     * Clears the text of touchSpace by assigning to empty array.
     */
    public void clearText() {
        String[] empty = new String[rows];
        for(String line: empty){
            line = "";
        }
        this.setText(empty);
    }
   
    /**
     * Shows a character within digit display.
     * @param in - Character to be displayed
     */
    public void setChar(char in) {
        this.digitLabel.setText(String.valueOf(in));
    }
    
    /**
     * Get the maximum number of rows displayed over the digit
     * @return Integer – number of rows.
     */
    public int getRows(){
        return rows;
    }
    
        /**
     * Set the color of the digit
     * @param color
     */
    public void setColor(TestClock.colorScheme color){ // TODO: Support enum
        Color colorBG;  // Background Color
        Color colorDig; // Digit Color
        Color colorText;// Text Color
        
        switch (color) {
            case SLATE: // Slate:
                colorBG = new java.awt.Color(82, 82, 82);       // Dark Grey
                colorDig = new java.awt.Color(229, 236, 233);   // Off-white
                colorText = new java.awt.Color(194, 193, 165);  // Light grey
                break;
                
            case WHITEBLACK: // For Alarm Clock Flash
                colorBG = new java.awt.Color(255, 255, 255);    // White
                colorDig = new java.awt.Color(0, 0, 0);         // Black
                colorText = colorBG;
                break;
                
            case BLACKWHITE: // Inverted Alarm Clock Flash
                colorBG = new java.awt.Color(0, 0, 0);          // White
                colorDig = new java.awt.Color(255, 255, 255);   // Black
                colorText = colorDig;
                break;
                
            default: // Green/Black
                colorBG = new java.awt.Color(0, 0, 0);          // Black
                colorDig = new java.awt.Color(51, 255, 0);      // Green
                colorText = colorDig;
                break;
        }
        
        // Set Text and Digit colors
        textSpace.setForeground(colorText);
        digitLabel.setForeground(colorDig);
        // Set JLayeredPane background
        setBackground(colorBG);
        setOpaque(true);

    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textSpace = new javax.swing.JTextPane();
        digitLabel = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1000, 1000));
        setPreferredSize(new java.awt.Dimension(154, 308));

        textSpace.setEditable(false);
        textSpace.setBorder(null);

        textSpace.setFont(new java.awt.Font("Courier New", 0, 23)); // NOI18N
        textSpace.setForeground(new java.awt.Color(51, 255, 0));
        textSpace.setAutoscrolls(false);
        textSpace.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        textSpace.setFocusable(false);
        textSpace.setMaximumSize(new java.awt.Dimension(1000, 1000));
        textSpace.setOpaque(false);
        textSpace.setPreferredSize(new java.awt.Dimension(154, 308));
        textSpace.setRequestFocusEnabled(false);
        textSpace.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textSpaceMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textSpaceMouseReleased(evt);
            }
        });
        add(textSpace);
        textSpace.setBounds(0, 0, 154, 308);
        SimpleAttributeSet lineSpacing = new SimpleAttributeSet();
        StyleConstants.setLineSpacing(lineSpacing, (float) 0.04);
        textSpace.setParagraphAttributes(lineSpacing, false);

        digitLabel.setFont(new java.awt.Font("Courier New", 0, 240)); // NOI18N
        digitLabel.setForeground(new java.awt.Color(51, 255, 0));
        digitLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        digitLabel.setText("0");
        digitLabel.setToolTipText("");
        digitLabel.setAlignmentX(0.5F);
        digitLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        digitLabel.setMaximumSize(new java.awt.Dimension(1000, 1000));
        digitLabel.setMinimumSize(new java.awt.Dimension(0, 0));
        digitLabel.setPreferredSize(new java.awt.Dimension(154, 308));
        add(digitLabel);
        digitLabel.setBounds(0, 0, 154, 308);
    }// </editor-fold>//GEN-END:initComponents

    private void textSpaceMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textSpaceMousePressed
        int touchedRegion = evt.getY()/(Y/rows);
        notifyListeners(touchedRegion, -1);
        touched = touchedRegion;
    }//GEN-LAST:event_textSpaceMousePressed

    private void textSpaceMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textSpaceMouseReleased
        int touchedRegion = evt.getY()/(Y/rows);
        notifyListeners(touchedRegion, touched);
        touched = -1;
    }//GEN-LAST:event_textSpaceMouseReleased
    

    /**
     * Utility function for notifying all EventListeners in listeners arrayList.
     * @param row – the row currently touched
     * @param touch – the row previously touched (-1 if none)
     */
    private void notifyListeners(int row, int touch) {
        TouchEvent localTouchEvent = new TouchEvent(row, touch, this);

        for(TouchListener listener: listeners){
            if (touch == -1) {
                listener.touchInitiated(localTouchEvent);
            } else if (row == touch) {
                listener.touchReleased(localTouchEvent);
            } else {
            listener.touchCancelled(localTouchEvent);
            }
        }
    }    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel digitLabel;
    private javax.swing.JTextPane textSpace;
    // End of variables declaration//GEN-END:variables

    private ArrayList<TouchListener> listeners = null;
    private int touched = -1;
    
    private int X = 154;
    private int Y = 308;
    private int rows = 11;
    private int columns = 12;
    
    private int textAlignment;

}
