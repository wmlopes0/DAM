# Sprite Library component in Unity

The **Sprite Library** component defines which [Sprite Library Asset](SL-Asset.md) a GameObject refers to at runtime. When you attach this component to a GameObject, the [Sprite Resolver component](SL-Resolver.md) attached to the same GameObject or child GameObject will refer to the Sprite Library Asset set by the Sprite Library component. This allows you to change the Sprite referenced by a [Sprite Renderer](https://docs.unity3d.com/Manual/class-SpriteRenderer) with the Sprite Resolver component.

## Property settings

In the Sprite Library component’s Inspector window, assign the desired Sprite Library Asset to the **Sprite Library Asset** property.

![](images/2D-animation-SLComp-properties.png)

After assigning a Sprite Library Asset, the Inspector window shows a visual preview of the content in the selected Sprite Library Asset.

![](images/2D-animation-SLComp-preview.png)

## Component functions

Within the Sprite Library component Inspector window, you can [commit the same overrides](SL-Main-Library.md) to the assigned Sprite Library Asset as you would to the **Main Library** in the [Sprite Library Editor](SL-Editor.md) window. You add or remove new Categories, add or remove new Labels in a Category, and change the sprite a Label refers to.

## Modified Sprites
![](images/2D-animation-SLAsset-category-entry-icon.png)<br/>_Example: A modified sprite retrieved from the Sprite Library Asset._

The **+** icon appears at the upper left of a Label entry when:

- You add a new Label to a Category from the retrieved Sprite Library Asset.
- You change the sprite reference of a Label from the original sprite reference retrieved from the Sprite Library Asset.

## Category and Label name conflict behavior

The following are the ways Unity resolves any name conflicts that may occur when you replace the assigned Sprite Library Asset in the **Sprite Library Asset** property with another Sprite Library Asset.

- If the same Category name already exists in the current set Sprite Library Asset, then Unity merges the Labels from Categories with the same name in both Sprite Library Assets into a single Category with that name.

- If there are Labels with the same name within the same Category when you assign the Sprite Library Asset, then Unity merges the Labels into a single Label. The merged Label uses the sprite reference from the replacement Sprite Library Asset instead.

**Note:** When you remove a Sprite Library Asset from the **Sprite Library Asset** property, overrides aren't saved to that Sprite Library Asset. All changes remain in the Sprite Library component.

## Additional resources
- [Swapping Sprite Library Assets](SLASwap.md)
- [Overrides to the Main Library](SL-Main-Library.md) 