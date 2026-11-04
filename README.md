# Epic Fight Twinblade

NeoForge 1.21.1 addon for Epic Fight `21.17.3.1`.

## Current features

- `twinblade` — a diamond-tier, two-handed double-bladed spear.
- A Twinblade Epic Fight weapon preset and moveset.
- `Twinblade Discipline` — a passive Epic Fight skill that grants +20% attack speed while equipped by the moveset.
- English, Russian, Spanish, German, French, and Simplified Chinese translations.

The first playable moveset deliberately uses Epic Fight's spear animations. It is therefore functional before custom art is available.

## Build and test

Install JDK 21, then run:

```powershell
gradle :neoforge:build
gradle :common:test
```

Epic Fight is downloaded automatically from Modrinth Maven. The NeoForge dependency metadata requires Epic Fight at runtime.

## Custom Twinblade animations

1. Download the Epic Fight Player Animation Rig and the Epic Fight Blender Exporter from the official Epic Fight API documentation.
2. In Blender, animate a biped rig for idle, walk, run, guard, two combo attacks, dash attack, and air attack.
3. Export the animation JSON files into `neoforge/src/main/resources/assets/twinblade/animations/`.
4. Register matching `AnimationAccessor` values in a `TwinbladeAnimations` class via Epic Fight's `AnimationRegistryEvent`.
5. Replace the `Animations.SPEAR_*` entries in `TwinbladeMovesets` with the new accessors.

Keep the same armature and do not reuse Epic Fight's own assets in a release; create or license original animation data.

## Adding Fabric and Forge later

The `common` module intentionally contains balance rules and cross-loader contract tests only. Add one platform module per loader:

1. Include `fabric` or `forge` in `settings.gradle`.
2. Create its Loom/Architectury build file and loader entrypoint.
3. Depend on the Epic Fight artifact/API for the compatible Minecraft version.
4. Register the item, Epic Fight registries, and assets in that platform module.
5. Reuse `TwinbladeBalance.ATTACK_SPEED_BONUS` and run the same `common:test` contract suite.

Fabric support must wait for an Epic Fight Fabric build/API compatible with the target Minecraft version. Forge and NeoForge have different event packages; isolate those registrations in their platform modules.
