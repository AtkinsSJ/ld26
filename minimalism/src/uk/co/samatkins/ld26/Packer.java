package uk.co.samatkins.ld26;

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
public class Packer {
        public static void main (String[] args) throws Exception {
                TexturePacker2.process("../minimalism-android/assets/unpacked", "../minimalism-android/assets", "packed");
        }
}