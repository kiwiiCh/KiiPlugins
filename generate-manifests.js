// Run this script once to generate all manifest files
// node generate-manifests.js

const fs = require("fs");
const path = require("path");

const plugins = [
  { name: "NoTrack",            desc: "Disables Discord analytics and Sentry crash reporting.",      version: "1.0.0" },
  { name: "MessageLogger",      desc: "Logs deleted and edited messages locally.",                   version: "1.0.0" },
  { name: "AlwaysTrust",        desc: "Removes the untrusted domain popup when tapping links.",      version: "1.0.0" },
  { name: "BetterChatbox",      desc: "Adds a character counter to the chat input.",                 version: "1.0.0" },
  { name: "ShowNSFW",           desc: "Bypasses the NSFW age gate on channels.",                     version: "1.0.0" },
  { name: "NoTypingIndicator",  desc: "Hides the typing indicator in channels.",                     version: "1.0.0" },
  { name: "PronounDB",          desc: "Shows pronouns on user profiles via pronoundb.org.",          version: "1.0.0" },
  { name: "KiiSettings",        desc: "Vencord-style plugin manager UI for KiiPlugins.",             version: "1.0.0" },
];

const base = path.join(__dirname, "KiiPlugins/src/main/kotlin/com/kii/plugins");

plugins.forEach(p => {
  const dir = path.join(base, p.name.toLowerCase());
  fs.mkdirSync(dir, { recursive: true });
  const manifest = {
    name: p.name,
    description: p.desc,
    version: p.version,
    authors: [{ name: "Kii", id: 0 }],
    links: {},
    changelog: "Initial release",
    changelogMedia: ""
  };
  fs.writeFileSync(path.join(dir, "manifest.json"), JSON.stringify(manifest, null, 2));
  console.log(`✓ ${p.name}/manifest.json`);
});
