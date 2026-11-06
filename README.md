# Daplow's Mob Revamp By Satjot Singh

A Fabric mod for Minecraft 1.19.2 that revamps 8 vanilla mobs to be more useful with tons of new features.

---

Link: https://modrinth.com/mod/daplow-mob-revamp


# Currently Added:
- 10 new items
- 2 new block
- 3 new potion effects
- 2 new weapons
- 2 new chestplate
- 1 new leggings
- 2 new structures

## 🚀 SUPER Impressive Features:
* **Custom AI & Entity Behavior Overrides (Piglin Chestplate):**
  Engineered custom behavioral logic for aggressive mobs (Piglin Brutes) by injecting bytecode via Mixins into the `PiglinEntity` and `PiglinEntityBrain` classes. Successfully bypassed default aggro logic to implement a custom neutral faction-state system based on player equipment.
* **Procedural Entity Animation & State Injection (Warden Custom Attack):**
  Developed a custom area-of-effect ground-slam attack for the Warden mob. Utilized advanced mixins across `WardenEntity` and `WardenEntityModel` to execute custom skeletal animations dynamically, synchronizing server-side physics (launching nearby entities) with client-side rendering without breaking entity state machines or animation layering.
* **Custom First-Person Render Pipeline Injection (Guardian Staff):**
  Created a custom weapon ("Guardian Staff") featuring unique attack mechanics. Intercepted the client rendering pipeline by writing a custom mixin for the `HeldItemRenderer` class to inject custom first-person transform matrices and animation states into the rendering loop.
* **Custom Dynamic Entity Shader/Renderer (Wither Chestplate):**
  Engineered a specialized `WitherChestplateRenderer` class to render a dynamic, translucent energy shield overlay (mimicking the Wither boss aesthetic) instead of relying on standard static armor textures, utilizing custom vertex consumer rendering and render-layer states.

## 🛠️ Tech Stack & Architecture
* **Language:** Java
* **Mod Loader:** Fabric API
* **Build Tool:** Gradle
