# Apple Flinger

[![build status](https://gitlab.com/ar-/apple-flinger/badges/master/build.svg)](https://gitlab.com/ar-/apple-flinger/builds) 
[![CII Best Practices](https://bestpractices.coreinfrastructure.org/projects/2233/badge)](https://bestpractices.coreinfrastructure.org/projects/2233)
[![Translation status](https://hosted.weblate.org/widgets/apple-flinger/-/translations/svg-badge.svg)](https://hosted.weblate.org/engage/apple-flinger/?utm_source=widget)

*Funny single- and multiplayer game for Android - Use a slingshot to shoot with apples. Very challenging and addictive.*

![screenshot](https://gitlab.com/ar-/apple-flinger/raw/master/metadata/en-AU/images/featureGraphic.png)

Features:

*    high resolution quality graphics
*    realistic physics
*    lag-free and smooth gameplay
*    fine detailed animations and particle systems
*    brand new and innovative game concept
*    single/multiplayer
*    100% open source (GPL3)

You use a slingshot to shoot with apples. Be first to destroy the whole enemy base, but be aware, the other side shoots back. This game has a balanced mix of puzzle, strategy, patience and action.

This is for one or two players! You can play this against anyone who sits next to you - or against yourself. You can also play against a computer controlled opponent. More features, more fun and more levels will come soon.

international age ratings:

*    ACB: G (general)
*    ClassInd: L
*    ESRB: E (everyone)
*    PEGI:3
*    USK: 0
*    IARC: 3

## Rules

### How to win a round ?

Drag the apple and shoot it towards the funny little enemies on the other side. **The player who destroys all enemies on the other side first, is the winner.**

Since player 1 has always the first shot, player 2 will always be allowed to perform the last shot. Same chances for both.

If both players destroy all enemies in the same round, the player with most points wins.

You can win the round, even if you have less points, by destroying all targets with less shots.

### What about the points ?

The points decide, who is the winner, when both players finish the game equally quick.

The points also accumulate, when you play many rounds in a row. So you can try to achieve new high-scores by playing 18 rounds in a row. 

If you play 18 rounds against another human you can decide if "most won rounds" or "most overall points" is the overall winner.

You get points by hitting targets and wood blocks. Each target has 1000 HP, so when you destroy it (with 1 or many shots) you will get up to 1000 HP. Hiting the targets on your own side grants 0 points. The wood blocks give less points, but it might still be a good strategy to destroy as much wood as possible on your way to the victory.

Additionally there are some unlockable achievements for gaining many points.

## Cheats

### To unlock all levels (v.1.5.10 and higher)

In the bottom right of the level-selection screen (where you choose the level 1-18) there is the GPL3 logo. Click or touch that 20 times in a row and all levels will be unlocked permanently.

## Quickstart

Option 1: [![Get it on F-Droid](https://f-droid.org/wiki/images/3/31/F-Droid-button_get-it-on_smaller.png)](https://f-droid.org/packages/com.gitlab.ardash.appleflinger.android/)

Option 2: Download the latest stable build from gitlab's master branch.
https://gitlab.com/ar-/apple-flinger/-/jobs/artifacts/master/download?job=build_release

Option 3: Download the latest release or an older release from
https://gitlab.com/ar-/apple-flinger/tags

Option 4: Build it from source

	git clone https://gitlab.com/ar-/apple-flinger.git
	cd apple-flinger
	./gradlew assembleRelease
	# note: your APK file is now here: ./android/build/outputs/apk/android-release-unsigned.apk

Option 5: Get if from google play store: [click here](https://play.google.com/store/apps/details?id=com.ardash.appleflinger.android)

## Contributors

See the [AUTHORS](AUTHORS.md) file for a list of all contributors. Merged changes from all authors will automatically add them to this file. please let me know if you don't want to be listed in [AUTHORS](AUTHORS.md) or if you are missing your name.

All merge requests are welcome. Contributions can be made towards:
Programming, Testing, Graphic Design, Web page, Sound engineering, Level design, Documentation, Consulting, ...

Send an email to <incoming+ar-/apple-flinger@gitlab.com> if you need help with anything.

## Create Levels
If you like to create levels (or maps or missions or episodes), There is a wiki-page for it: https://gitlab.com/ar-/apple-flinger/wikis/create-new-level . Check it out! Please send your levels, no matter if you make 1 or 100. All contributions are welcome.

## Translate

You can translate the properties file and send a merge request, or (for the technically less experienced) you can simply use the convinient web-interface on weblate https://hosted.weblate.org/engage/apple-flinger/ . There you can add new languages to the game and improve existing translations. By default you will be mentionend in the AUTHORS file of this project (that is done by a script). Let me know by email, if you don't want that.

Here are some Weblate statistics:
[![Translation status](https://hosted.weblate.org/widgets/apple-flinger/-/translations/287x66-grey.png)](https://hosted.weblate.org/engage/apple-flinger/?utm_source=widget)

[![Translation status](https://hosted.weblate.org/widgets/apple-flinger/-/translations/multi-auto.svg)](https://hosted.weblate.org/engage/apple-flinger/?utm_source=widget)

## How to report an error? How to suggest a new feature?
If you don't have a GitLab account (and don't want to create one), you can send an email to <incoming+ar-/apple-flinger@gitlab.com> or to <ar-appleflinger@abga.be>. This will automatically create an issue here in the Gitlab system.

If you have a GitLab account: you can [CLICK HERE](https://gitlab.com/ar-/apple-flinger/issues/new) to raise a new issue. 

The issue tracking is used for both: bugs and feature requests.

# Donate

Cash donations are not accepted. You can buy the author of this app a coffee if you have some spare cryptocurrencies.

* BTC/BCH/BTG/etc: 1J2bbhJYksSjeynGGhuSPN9aTEaxiGm4nR
* BTC Bech32: bc1qgshj3mtju02sg9ymse9cksfjdjh5gp0204w3zj
* DASH: XbLRt5imEHc72KmhvC7V9v8f9NmYrmvweS
* FIRO: a4tAW5vp8rzjFrAxhRaq24m6vFZ2AmHUYs
* ETH: 0x0a6604dc5000c57e80f824601535db216e77482f
* XMR: 4AffoFbFhfGZdBeMaQYSCMTURacM3qZYxKHQeLx8xkiLUjzk2GPzjCrNU5uquXEsEL6wcN8b5ULg5JdDaQfuQRkUJs6xx3f

*Note: These addresses are taken from the original autors website (http://andreas.redmer.super-sm.art/). They are cryptographically signed, with the same key, that signed the git commits of this software project. Feel free to verify the GPG signatures so you can be sure, that you donation goes to the person, who actually commited the code of this software.*

How much to donate? 🙂

1. Go to your nearest coffee shop (or bar [or cafe in the Netherlands]).
2. Get the price for a regular coffee (or beer). No sugar.
3. Optionally multiply by 2. Thanks.
4. Convert the price into a crypto currency mentioned above. 
5. Donate the resulting value.

✌️

See also: [DONATE](DONATE.md) file
