# HySuite

<div align="center">

![HySuite Logo](images/logo.svg)

**Complete Server Essentials for Hytale**

[![Version](https://img.shields.io/badge/version-1.0.1-blue.svg)](https://github.com/Kckonicki/HySuite/releases)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE.md)
[![Documentation](https://img.shields.io/badge/docs-available-brightgreen.svg)](https://kckonicki.github.io/HySuite/)

[**Documentation**](https://kckonicki.github.io/HySuite/) • [**Download**](https://github.com/Kckonicki/HySuite/releases) • [**Discord**](https://discord.gg/vPHmVASQAN)

</div>

---

## 🌟 Features

HySuite is an all-in-one essentials plugin for Hytale servers, providing:

### Teleportation
- **TPA System** - Request, accept, deny teleports with cooldowns
- **Home System** - Multiple homes with beautiful GUI management
- **Warp System** - Server-wide warp points for quick navigation
- **Spawn System** - Set and teleport to spawn
- **Utility Commands** - Back, random teleport, and more

### Rank Management
- **GUI-Based Management** - Easy-to-use rank editor interface
- **Permission System** - Fine-grained permission control
- **Player Assignment** - Assign ranks through GUI
- **Priority System** - Hierarchical rank structure

### Communication
- **Private Messaging** - Send private messages between players
- **Admin Chat** - Multiple admin chat channels (staff, admin)
- **Color Support** - Full MiniMessage tags and hex colors

### Admin Tools
- **Vanish Mode** - Become invisible to players
- **Fly Mode** - Toggle flight for admins
- **Player Info** - View detailed player information
- **Teleport Management** - Admin teleport commands

### Quality of Life
- **Cooldown System** - Configurable command delays
- **VIP Perks** - Special benefits for VIP players
- **Multi-Language** - English and French support
- **GUI Interfaces** - 12 beautiful user interfaces

---

## 📦 Installation

### Quick Start

```bash
# 1. Download
# Get hysuite-*.jar from releases

# 2. Install
copy hysuite-*.jar %HYTALE_HOME%/Server/plugins/

# 3. Start
# Server loads automatically, creates default config
```

**See:** [Complete Installation Guide](https://kckonicki.github.io/HySuite/docs/installation.html)

---

## 🎮 Commands

### Player Commands
```
/tpa, /tpahere, /tpaccept, /tpdeny, /tpcancel
/sethome, /home, /delhome, /homes
/warp, /warps, /spawn, /back, /rtp
/msg, /reply, more to come!
```

### Admin Commands
```
/hysuite (or /hys)
  ├── rank         - Rank management GUI
  ├── assign       - Assign ranks GUI
  ├── setrank      - Set player rank
  ├── playerinfo   - Player information
  └── reload       - Reload configuration

/setwarp, /delwarp, /setspawn
/vanish, /fly, /tp, /tphere
/adminchat
```

**See:** [Complete Commands Reference](https://kckonicki.github.io/HySuite/docs/commands.html)

---

## 🔑 Permissions

All permissions use the `hysuite.*` prefix:

### Admin
```
hysuite.admin.ranks       - Rank management
hysuite.admin.setrank     - Set player ranks
hysuite.admin.reload      - Reload config
```

### Utility
```
hysuite.vanish           - Vanish mode
hysuite.fly              - Fly mode
hysuite.setwarp          - Create warps
```

### VIP
```
hysuite.vip              - VIP status
hysuite.vip.homes        - Extra home slots
hysuite.cooldown.bypass  - Skip all delays
```

**See:** [Complete Permissions List](https://kckonicki.github.io/HySuite/docs/permissions.html)

---

## ⚙️ Configuration

### Main Settings
```json
{
  "language": "en",
  "teleportWarmup": 5,
  "teleportCooldown": 30,
  "maxHomes": 3,
  "vipMaxHomes": 10
}
```

**Location:** `Server/data/plugins/HySuite/config.json`

**See:** [Configuration Guide](https://kckonicki.github.io/HySuite/docs/config.html)

---

## 📊 Statistics

- **Commands:** 25+
- **Permissions:** 17
- **GUI Interfaces:** 12
- **Languages:** 2 (English, French, More to come!)
- **Dependencies:** 0 (All-in-one!)

---

## 🚀 Key Highlights

✅ **Zero Dependencies** - Single plugin, no external requirements  
✅ **Beautiful GUIs** - Modern interfaces players love  
✅ **Production Ready** - Tested and stable  
✅ **Well Documented** - Comprehensive guides  
✅ **Active Development** - Regular updates  
✅ **Open Source** - MIT License 

---

## 📚 Documentation

Complete documentation available at: **[kckonicki.github.io/HySuite](https://kckonicki.github.io/HySuite/)**

- [Installation Guide](https://kckonicki.github.io/HySuite/docs/installation.html)
- [Commands Reference](https://kckonicki.github.io/HySuite/docs/commands.html)
- [Permissions List](https://kckonicki.github.io/HySuite/docs/permissions.html)
- [Configuration Guide](https://kckonicki.github.io/HySuite/docs/config.html)

---

## 🐛 Issues & Support

Found a bug? Have a suggestion?

- **Report Issues:** [GitHub Issues](https://github.com/Kckonicki/HySuite/issues)
- **Discord:** [Join Our Server](https://discord.gg/vPHmVASQAN)
- **Documentation:** [Help Center](https://kckonicki.github.io/HySuite/)

---

## 🤝 Contributing

Contributions are welcome! Please:

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request

See [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

---

## 📝 License

This project is licensed under the MIT License - see [LICENSE.md](LICENSE.md) for details.

### Credits

- **Created by:** Buddytohelpu
- **Original Inspiration:** Hyssentials by Leclowndu93150
- **Built for:** Hytale by Hypixel Studios

---

## 🎯 Roadmap

### v1.0.1 (Released!)
- [✅] Huge UI/GUI Enhancements
- [✅] More commands
- [ ] Player Info (Planned for v1.0.2)
- [ ] Settings (Planned for v1.0.2)
- [✅] Main HySuite Dashboard

### v1.1.0 (Planned)
- [ ] Economy system
- [ ] Shop system
- [ ] Kit system
- [ ] Enhanced home features (sharing, categories)
- [ ] Leaderboards

### v1.2.0 (Future)
- [ ] Quest system
- [ ] Achievement tracking
- [ ] Friend system
- [ ] Party system
- [ ] Auction house

See [ROADMAP.md](ROADMAP.md) for detailed plans.

---

## 📊 Version History

### v1.0.1 (January 28, 2026)
New Features
✅ New /hys dashboard command
✅ New HySuite Dashboard UI
✅ Minor bug fixes
✅ Documentation Updates
✅ POM Update

---

## ⭐ Star History

[![Star History Chart](https://api.star-history.com/svg?repos=Kckonicki/HySuite&type=Date)](https://star-history.com/#Kckonicki/HySuite&Date)

---

<div align="center">

**Made with ❤️ for the Hytale Community**

[Website](https://kckonicki.github.io/HySuite/) • [Documentation](https://kckonicki.github.io/HySuite/docs/installation.html) • [Discord](https://discord.gg/vPHmVASQAN)

</div>
