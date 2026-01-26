# 🗺️ HySuite Roadmap

This roadmap outlines planned features and improvements for HySuite. **All information is subject to change at any moment** based on community feedback, technical feasibility, and development priorities.

**Current Version:** 1.0.0  
**Next Release:** 1.0.1 (Q1 2026)

---

## 📋 Table of Contents

- [Version 1.0.1 - UI/UX Enhancements](#version-101---uiux-enhancements)
- [Version 1.1.0 - Core Features](#version-110---core-features)
- [Version 1.2.0 - Social & Economy](#version-120---social--economy)
- [Version 2.0.0 - Major Expansion](#version-200---major-expansion)
- [Future Considerations](#future-considerations)
- [Community Requests](#community-requests)

---

## ⚠️ Important Notes

- **All dates are estimates** - Features may be delayed or rescheduled
- **Order may change** - Based on community feedback and priorities
- **Features may be added/removed** - Roadmap is fluid and adaptive
- **Subject to change** - Everything in this roadmap can change at any moment
- **Breaking changes** - Will be clearly marked and documented
- **Feedback welcome** - Suggest features via GitHub issues

---

## 🎨 Version 1.0.1 - UI/UX Enhancements

**Target:** Q1 2026 (Feb-Mar)  
**Focus:** Improved user experience and interface polish

### 1. Dashboard System

**New Command:** `/hys dashboard`

**Features:**
```
┌─────────────────────────────────────┐
│  HySuite Dashboard                  │
├─────────────────────────────────────┤
│  ⌂ Homes: 3/10                      │
│  ⚡ Warps: 15 available             │
│  👥 Rank: VIP                       │
│  💰 Balance: $5,000                 │
│  📊 Playtime: 24h 15m               │
├─────────────────────────────────────┤
│  [Manage Homes] [View Warps]        │
│  [Player Stats] [Settings]          │
└─────────────────────────────────────┘
```

- Quick stats overview
- Recent activity feed  
- Quick action buttons
- Visual progress bars
- Server announcements integration

**New Files:**
- `HySuite_Dashboard.ui`
- `DashboardGui.java`
- `DashboardData.java`

---

### 2. Enhanced Home Management

**New Commands:**
- `/home share <player> <home>` - Share home with specific player
- `/home privacy <home>` - Change home privacy settings
- `/home description <home> <text>` - Add description to home
- `/home icon <home> <icon>` - Set custom icon for home

**Features:**
```
┌─────────────────────────────────────┐
│  Home: "Main Base"                  │
├─────────────────────────────────────┤
│  🏠 Icon: [Select]   🎨 Color: [▣] │
│  📝 Description: My main home       │
│  🔒 Privacy: [Public ▼]             │
│  👥 Shared with: 3 players          │
│  📅 Created: Jan 24, 2026           │
├─────────────────────────────────────┤
│  [Teleport] [Edit] [Share] [Delete] │
└─────────────────────────────────────┘
```

- Home icons (10+ preset icons)
- Color coding for homes
- Home descriptions
- Privacy settings (Public/Friends/Private)
- Home sharing system
- Home favorites (star most-used)
- Sort/filter options (name, date, distance)
- Home preview (coordinates, world, biome)

**Updated Files:**
- `HySuite_HomeList.ui` - Enhanced interface
- `HySuite_HomeEntry.ui` - New fields
- `HomeListGui.java` - New features
- `HomeManager.java` - Sharing logic

---

### 3. Warp Categories & Management

**New Commands:**
- `/setwarp <name> <category>` - Create warp with category
- `/warp category <category>` - List warps in category
- `/warp vote <name>` - Vote for favorite warp
- `/warp featured` - Show featured/most voted warps

**Features:**
```
┌─────────────────────────────────────┐
│  Server Warps                       │
├─────────────────────────────────────┤
│  Categories:                        │
│  📍 [Spawn Points]  🏪 [Shops]     │
│  ⚔️ [PvP Zones]     🎮 [Minigames] │
│  🏛️ [Cities]        🌳 [Resources] │
├─────────────────────────────────────┤
│  Spawn Points:                      │
│  • Main Spawn     [Teleport]        │
│  • Nether Hub     [Teleport]        │
│  • Dungeon        [Teleport]        │
└─────────────────────────────────────┘
```

- Warp categories (6 default categories)
- Icons for each warp
- Vote system (players vote favorites)
- Warp descriptions
- Visit counter
- Featured warps
- Search/filter functionality

**Updated Files:**
- `HySuite_WarpList.ui` - Category support
- `WarpListGui.java` - Enhanced features
- `WarpManager.java` - Category logic

---

### 4. Player Profile System

**New Command:** `/hys profile [player]`

**Features:**
```
┌─────────────────────────────────────┐
│  Player: Buddytohelpu               │
├─────────────────────────────────────┤
│  👤 Rank: Admin                     │
│  ⭐ Level: 25                       │
│  💰 Balance: $15,000                │
│  📅 Joined: Dec 1, 2025             │
│  ⏱️ Playtime: 156h 32m              │
│  🏆 Achievements: 24/50             │
├─────────────────────────────────────┤
│  Stats:                             │
│  • Homes: 8/10                      │
│  • Warps Created: 5                 │
│  • TPAs Sent: 142                   │
│  • Messages Sent: 1,284             │
└─────────────────────────────────────┘
```

- Player statistics tracking
- Playtime tracking
- Rank history
- Social stats
- Customizable profile (bio, favorite home)

**New Files:**
- `HySuite_PlayerProfile.ui`
- `PlayerProfileGui.java`
- `PlayerStats.java`

---

### 5. Settings GUI

**New Command:** `/hys settings`

**Features:**
```
┌─────────────────────────────────────┐
│  HySuite Settings                   │
├─────────────────────────────────────┤
│  🔔 TPA Notifications: [✓] Enabled  │
│  📱 Message Sounds: [✓] Enabled     │
│  🌐 Language: [English ▼]           │
│  🎨 Theme: [Default ▼]              │
│  🔒 Home Privacy: [Friends Only ▼]  │
│  ⏰ Auto-AFK: [✓] 10 minutes        │
├─────────────────────────────────────┤
│  [Save Changes] [Reset to Default]  │
└─────────────────────────────────────┘
```

- Notification preferences
- Default privacy settings
- Language selection per player
- UI theme selection
- Auto-save settings

**New Files:**
- `HySuite_Settings.ui`
- `SettingsGui.java`
- `PlayerSettings.java`

---

### Bug Fixes & Improvements

- Performance optimizations for GUI rendering
- Memory usage improvements
- Better error handling
- Enhanced logging
- Code cleanup and refactoring

---

## 🚀 Version 1.1.0 - Core Features

**Target:** Q2 2026 (Apr-Jun)  
**Focus:** New gameplay systems  
**Status:** Planning phase

### Economy System

**New Commands:**
```
/balance [player]              - Check balance
/pay <player> <amount>         - Send money to player
/baltop [page]                 - Richest players leaderboard
/eco give <player> <amount>    - Admin: Give money
/eco take <player> <amount>    - Admin: Take money
/eco set <player> <amount>     - Admin: Set balance
/eco reset <player>            - Admin: Reset balance
```

**Features:**
- Complete economy system
- Transaction history
- Balance display in dashboard
- Pay menu (select player + amount)
- Rich list leaderboard
- Configurable starting balance
- Currency symbol customization
- Max balance limits

**Config Options:**
```json
{
  "economy": {
    "enabled": true,
    "startingBalance": 1000,
    "currencySymbol": "$",
    "currencyName": "Coins",
    "maxBalance": 999999999,
    "enableTransactionLog": true
  }
}
```

**New Files:**
- `EconomyManager.java`
- `Transaction.java`
- `BalanceCommand.java`
- `PayCommand.java`
- `EcoCommand.java`

---

### Kit System

**New Commands:**
```
/kit                           - Open kit GUI
/kit <name>                    - Claim specific kit
/kit list                      - List all available kits
/kit create <name>             - Admin: Create kit
/kit edit <name>               - Admin: Edit kit
/kit delete <name>             - Admin: Delete kit
/kit give <player> <kit>       - Admin: Give kit to player
```

**Features:**
```
┌─────────────────────────────────────┐
│  Available Kits                     │
├─────────────────────────────────────┤
│  🎁 Starter Kit                     │
│     Cooldown: 24 hours              │
│     [Claim] Next: 5h 23m            │
│                                     │
│  💎 VIP Kit                         │
│     Requires: VIP Rank              │
│     [Claim] Available now!          │
│                                     │
│  ⚔️ PvP Kit                         │
│     Cooldown: 1 hour                │
│     [Claim] Available now!          │
└─────────────────────────────────────┘
```

- Multiple kit types (starter, VIP, daily, etc.)
- Cooldown system per kit
- Rank-based kits
- Kit preview before claiming
- Auto-delivery option
- One-time kits

---

### Leaderboards System

**New Command:** `/leaderboard [type]` or `/lb [type]`

**Leaderboard Types:**
- Richest players
- Most playtime
- Most homes
- Most warps created
- Quest completions (when added)
- TPA requests sent
- Messages sent

**Features:**
```
┌─────────────────────────────────────┐
│  Leaderboards                       │
├─────────────────────────────────────┤
│  💰 Richest Players:                │
│  #1 Player1    $50,000              │
│  #2 Player2    $42,500              │
│  #3 Player3    $38,750              │
│  ...                                │
│  #15 YOU       $12,000              │
└─────────────────────────────────────┘
```

- Multiple leaderboard categories
- Player ranking display
- Top 100 support
- Refresh intervals
- Hologram displays (if supported)

---

## 🌐 Version 1.2.0 - Social & Economy

**Target:** Q3 2026 (Jul-Sep)  
**Focus:** Social features and commerce  
**Status:** Concept phase

### Shop System

**New Commands:**
```
/shop                          - Open server shop
/shop create <name>            - Create player shop
/shop set <item> <price>       - Add item to your shop
/shop edit                     - Edit your shop
/shop delete                   - Delete your shop
/shop visit <player>           - Visit player's shop
/shop list                     - List all player shops
```

**Features:**
- Server-run shops (admin controlled)
- Player-owned shops
- Shop signs in-world
- Transaction logs
- Shop search
- Featured shops
- Buy/sell system
- Stock management

---

### Auction House

**New Command:** `/ah` or `/auction`

**Features:**
```
┌─────────────────────────────────────┐
│  Auction House                      │
├─────────────────────────────────────┤
│  💎 Diamond x64                     │
│     Seller: Player123               │
│     Price: $5,000                   │
│     Expires: 2h 15m    [Buy Now]    │
│                                     │
│  ⚔️ Netherite Sword                 │
│     Enchanted: Sharpness V          │
│     Price: $10,000                  │
│     Expires: 5h 42m    [Buy Now]    │
└─────────────────────────────────────┘
```

- List items for sale
- Search & filter system
- Time-based expiration
- Transaction fees (configurable)
- Sales history
- Top sellers leaderboard
- Bidding system (future)

---

### Friends System

**New Commands:**
```
/friend add <player>           - Send friend request
/friend remove <player>        - Remove friend
/friend accept <player>        - Accept friend request
/friend deny <player>          - Deny friend request
/friend list                   - Open friends GUI
/friend online                 - List online friends
/friend teleport <player>      - Teleport to friend
/friend block <player>         - Block player
```

**Features:**
- Friend requests
- Friends list GUI
- Online/offline status
- Friend teleportation
- Friend-only homes
- Block system
- Friend notifications

---

### Party System

**New Commands:**
```
/party create                  - Create party
/party invite <player>         - Invite player
/party accept                  - Accept party invite
/party leave                   - Leave current party
/party kick <player>           - Kick party member
/party disband                 - Disband party
/party chat <message>          - Party chat
/party warp                    - Teleport party to you
```

**Features:**
- Party creation and management
- Party chat
- Party teleportation
- Leader controls
- Max party size (configurable)
- Party home sharing

---

## 🎮 Version 2.0.0 - Major Expansion

**Target:** Q4 2026 (Oct-Dec)  
**Focus:** Game-changing features  
**Status:** Early concept

### Quest System

**Features:**
- Daily quests
- Weekly quests
- Achievement quests
- Challenge quests (difficult, high reward)
- Quest chains
- Story quests
- Progress tracking
- Rewards system

**Quest Types:**
- Kill X mobs
- Mine X blocks
- Visit X locations
- Craft X items
- Trade with X players
- Earn X money

---

### Achievement System

**Features:**
- 100+ achievements
- Categories (combat, building, social, etc.)
- Hidden achievements
- Achievement points
- Rewards for achievements
- Showcase system
- Title system

---

### Advanced TPA Features

**Enhancements:**
- TPA history
- Favorite players (quick select)
- Blocked players list
- TPA statistics
- Request cooldown timers
- Auto-accept system (for friends/party)
- TPA bookmarks

---

### Advanced Analytics

**For Server Admins:**
- Plugin usage statistics
- Most used commands
- Popular warps/homes
- Active times analysis
- Player retention metrics
- Feature adoption tracking
- Performance metrics
- Exportable reports

---

## 🔮 Future Considerations

These features are under consideration for future versions. **All of this is subject to change.... ESPECIALLY since Hytale themselves are in constant development as things get deprecated and new things come out**

### Database Support
- MySQL/MariaDB
- PostgreSQL
- SQLite (enhanced)
- MongoDB
- Better performance with many players
- Cross-server synchronization

### Multi-Server Support (BungeeCord/Velocity)
- Network-wide homes
- Synchronized economy
- Cross-server TPAs
- Global chat
- Shared warps

### API & Integrations
- Public API for developers
- PlaceholderAPI support
- Economy plugin integration
- Permission plugin integration
- Webhook support

### Advanced Features
- Custom events system
- Scripting support (JavaScript/Lua)
- Advanced GUI builder
- Custom commands creator
- Module system (plugins for plugins)

### Premium Features **Don't like this as open source is always the way but we will see**
- HySuite Pro (paid version)
- Advanced analytics dashboard
- Priority support
- Custom branding removal
- Extended limits
- Cloud backups

---

## 💬 Community Requests

**Top Requested Features:**

Vote for features on GitHub Discussions!

1. **Waypoints System** - Personal markers on map
2. **Player Warps** - Let players create public warps
3. **Homes in Other Dimensions** - Support for Instance/Dungeon 
4. **Bank System** - Shared economy accounts
5. **Mail System** - Send items/messages offline
6. **Auction Bids** - Bidding on auction items
7. **Player Shops (Physical)** - Chest shops in-world
8. **Backpacks** - Portable storage
9. **Pets System** - Companion pets
10. **Minigames Integration** - Arena/lobby management

**Submit Your Ideas:** [GitHub Discussions](https://github.com/Kckonicki/HySuite/discussions)

---

## 📊 Development Priorities

### High Priority
- Bug fixes (always #1)
- Performance improvements
- Security updates
- Critical feature requests

### Medium Priority
- Quality of life improvements
- New features
- Documentation updates
- Community suggestions

### Low Priority
- Experimental features
- Beta features
- Nice-to-have additions

---

## 🗓️ Release Schedule

### Regular Updates
- **Patch releases** (1.0.x): Bug fixes, every 2-4 weeks
- **Minor releases** (1.x.0): New features, every 2-3 months
- **Major releases** (x.0.0): Major changes, every 6-12 months

---

## 📢 Staying Updated

**Follow Development:**
- **GitHub Releases** - Official releases
- **Discord** - Daily updates & discussions
- **Website** - Updates

**Provide Feedback:**
- Open GitHub issues for bugs
- Use Discussions for feature requests
- Join Discord for quick questions
- Submit PRs for contributions

---

## ⚠️ Deprecation Notices

### Planned Deprecations

No deprecations planned for current version. Future deprecations will be:
- Announced 3 months in advance (can change per update depending on Hytales on-going development)
- Documented in release notes
- Migration guides provided
- Backward compatibility maintained when possible

---

## 🎯 Version History

### Released Versions

**v1.0.0** (January 25, 2026)
- Initial public release
- Complete rebranding from Hyssentials
- 25+ commands
- 12 GUI interfaces
- 17 permissions
- Multi-language support (EN, FR, more to come)
- Full documentation

---

## 📝 Important Reminders

**Everything in this roadmap is subject to change at any moment:**
- Features may be added, removed, or modified
- Timelines are estimates and may shift
- Community feedback heavily influences priorities
- Technical limitations may affect implementation
- Some features may be merged or split across versions

**Want to Influence the Roadmap?**
- Participate in GitHub Discussions
- Vote on feature proposals
- Test beta features
- Report bugs early
- Share your use cases
- Contribute code

---

## 🎯 How to contribute

For how to help, see:
- **CONTRIBUTING.md** - How to contribute to development

---

**Last Updated:** January 26, 2026  
**Next Review:** March 1, 2026

---

*Have questions about the roadmap? Join our [Discord](https://discord.gg/vPHmVASQAN) or open a [Discussion](https://github.com/Kckonicki/HySuite/discussions) on GitHub!*

**Remember:** All information is subject to change. This roadmap is a living document that evolves with the project, Hytale (Hypixel) and the community needs.