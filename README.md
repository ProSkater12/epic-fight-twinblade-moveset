# Epic Fight Twinblade

NeoForge 1.21.1 addon for Epic Fight `21.17.3.1`.

## Project layout

- `common/` — shared gameplay code, assets, and unit tests. Not a Gradle project.
- `neoforge/` — NeoForge adapter and the only buildable module for now.
- Future `forge/` (or Fabric) modules will reuse `common/` the same way and provide their own adapters plus builds.

## Current features

- `twinblade` — a diamond-tier, two-handed double-bladed spear.
- A Twinblade Epic Fight weapon preset and moveset.
- `Twinblade Discipline` — a passive Epic Fight skill that grants +20% attack speed while equipped by the moveset.
- English, Russian, Spanish, German, French, and Simplified Chinese translations.

Locomotion (idle, walk, run) uses custom Twinblade clips. Remaining living motions and attacks still fall back to Epic Fight spear animations until those assets are wired.

## Build and test

Install JDK 21, then run:

```powershell
gradlew :neoforge:build
gradlew :neoforge:test
```

Epic Fight is downloaded automatically from Modrinth Maven. The NeoForge dependency metadata requires Epic Fight at runtime.

## Custom Twinblade animations

1. Download the Epic Fight Player Animation Rig and the Epic Fight Blender Exporter from the official Epic Fight API documentation.
2. In Blender, animate a biped rig for idle, walk, run, guard, two combo attacks, dash attack, and air attack.
3. Export the animation JSON files into `common/src/main/resources/assets/twinblade/animmodels/animations/` (Epic Fight resolves clips from `animmodels/animations/<accessor-path>.json`, not `assets/.../animations/`).
4. Register matching `AnimationAccessor` values in `TwinbladeAnimations` via Epic Fight's `AnimationRegistryEvent` (hooked from the NeoForge entrypoint).
5. Replace the remaining spear fallback entries in `TwinbladeMovesetDefinitions` with the new accessors.

Keep the same armature and do not reuse Epic Fight's own assets in a release; create or license original animation data.

## Adding Forge or Fabric later

1. Include the new loader module in `settings.gradle`.
2. Create its Loom/Architectury build file and point its `sourceSets` at `../common/src/main/...` and `../common/src/test/java`.
3. Depend on the Epic Fight artifact/API for the compatible Minecraft version.
4. Keep only loader entrypoint and DeferredRegister-style adapters in that module.
5. Reuse `TwinbladeBalance.ATTACK_SPEED_BONUS` and run the shared tests with the loader's `:test` task.

Fabric support must wait for an Epic Fight Fabric build/API compatible with the target Minecraft version.
