package org.example.prompt;

public enum PreviewPrompt {
        INFO("""
                        ______
               ╋╋┏┓╋╋╋╋╋╋╋╋┏━━━┓╋╋╋╋╋┏┓      Uɴɪᴠᴇʀsɪᴛʏ
               ╋╋┃┃╋╋╋╋╋╋╋╋┃┏━┓┃╋╋╋╋╋┃┃
               ╋╋┃┣━━┳┓┏┳━━┫┗━┛┣┓┏┳━━┫┗━┓
               ┏┓┃┃┏┓┃┗┛┃┏┓┃┏┓┏┫┃┃┃━━┫┏┓┃      ᴘʀᴏᴊᴇᴄᴛ
               ┃┗┛┃┏┓┣┓┏┫┏┓┃┃┃┗┫┗┛┣━━┃┃┃┃  "ᴄʀʏᴘᴛᴏᴀɴᴀʟʏᴢᴇʀ"
               ┗━━┻┛┗┛┗┛┗┛┗┻┛┗━┻━━┻━━┻┛┗┛
               
               
                   Tʜᴇʀᴇ ɪs ɴᴏ ғɪʟᴇ ᴀᴠᴀɪʟᴀʙʟᴇ ғᴏʀ ᴘʀᴇᴠɪᴇᴡ.
               
               
                   Hɪɴᴛ:
                    ɴ - ɴᴇxᴛ ᴘᴀɢᴇ
                    ᴘ - ᴘʀᴇᴠɪᴏᴜs ᴘᴀɢᴇ
               
               
                   ========================================
                             Developed by Aleksei Osadchii
                   ========================================
                                        """);

        private final String message;

        PreviewPrompt(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
}
