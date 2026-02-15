package entity.entities;

import entity.Entity;
import util.Ansi;

public class Goblin extends Entity {
    public Goblin() {
        super("Goblin", 25, 25, 2);
        super.highlightColor = Ansi.BG_GREEN;
        super.textColor = Ansi.GREEN;
        super.flavorDescription = """
                The small, dangly creature is haunched, swaying
                to and fro. A black liquid steadily drips from
                its mouth, creating a rhythmic beat as it patters
                to the floor.
                """;
    }

    public String getHighlightColor() { return this.highlightColor; }
    public String getTextColor() { return this.textColor; }
    public String getFlavorDescription() { return this.flavorDescription; }
}
