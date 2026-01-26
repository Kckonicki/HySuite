// HySuite Website JavaScript

// ============================================
// Smooth Scrolling
// ============================================
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();
        const target = document.querySelector(this.getAttribute('href'));
        if (target) {
            target.scrollIntoView({
                behavior: 'smooth',
                block: 'start'
            });
        }
    });
});

// ============================================
// Mobile Navigation Toggle
// ============================================
const mobileToggle = document.querySelector('.mobile-toggle');
const navMenu = document.querySelector('.nav-menu');

if (mobileToggle) {
    mobileToggle.addEventListener('click', () => {
        navMenu.classList.toggle('active');
        mobileToggle.classList.toggle('active');
    });
}

// ============================================
// Navbar Scroll Effect
// ============================================
let lastScroll = 0;
const navbar = document.querySelector('.navbar');

window.addEventListener('scroll', () => {
    const currentScroll = window.pageYOffset;
    
    if (currentScroll > 100) {
        navbar.style.boxShadow = '0 4px 12px rgba(0,0,0,0.15)';
    } else {
        navbar.style.boxShadow = '0 2px 10px rgba(0,0,0,0.1)';
    }
    
    lastScroll = currentScroll;
});

// ============================================
// Stats Counter Animation
// ============================================
const stats = document.querySelectorAll('.stat-number');

const observerOptions = {
    threshold: 0.5,
    rootMargin: '0px'
};

const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            const target = entry.target;
            const text = target.textContent;
            const number = parseInt(text.replace('+', ''));
            
            if (!isNaN(number)) {
                animateValue(target, 0, number, 2000);
                observer.unobserve(target);
            }
        }
    });
}, observerOptions);

stats.forEach(stat => observer.observe(stat));

function animateValue(element, start, end, duration) {
    const range = end - start;
    const increment = range / (duration / 16);
    let current = start;
    
    const timer = setInterval(() => {
        current += increment;
        if (current >= end) {
            element.textContent = end + '+';
            clearInterval(timer);
        } else {
            element.textContent = Math.floor(current) + '+';
        }
    }, 16);
}

// ============================================
// Feature Card Hover Effects
// ============================================
const featureCards = document.querySelectorAll('.feature-card, .community-card');

featureCards.forEach(card => {
    card.addEventListener('mouseenter', function() {
        this.style.borderColor = 'var(--primary)';
    });
    
    card.addEventListener('mouseleave', function() {
        this.style.borderColor = 'transparent';
    });
});

// ============================================
// Copy Code Block
// ============================================
const codeBlocks = document.querySelectorAll('.code-block');

codeBlocks.forEach(block => {
    const button = document.createElement('button');
    button.className = 'copy-btn';
    button.textContent = 'Copy';
    button.style.cssText = 'position:absolute;top:10px;right:10px;padding:5px 12px;background:var(--primary);color:white;border:none;border-radius:5px;cursor:pointer;font-size:0.85rem;';
    
    block.style.position = 'relative';
    block.appendChild(button);
    
    button.addEventListener('click', () => {
        const code = block.querySelector('code').textContent;
        navigator.clipboard.writeText(code).then(() => {
            button.textContent = 'Copied!';
            setTimeout(() => {
                button.textContent = 'Copy';
            }, 2000);
        });
    });
});

// ============================================
// Lazy Load Images
// ============================================
if ('IntersectionObserver' in window) {
    const imageObserver = new IntersectionObserver((entries, observer) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                const img = entry.target;
                img.src = img.dataset.src;
                img.classList.remove('lazy');
                imageObserver.unobserve(img);
            }
        });
    });

    const lazyImages = document.querySelectorAll('img.lazy');
    lazyImages.forEach(img => imageObserver.observe(img));
}

// ============================================
// Page Load Animation
// ============================================
window.addEventListener('load', () => {
    document.body.classList.add('loaded');
});

// ============================================
// External Link Icons
// ============================================
document.querySelectorAll('a[target="_blank"]').forEach(link => {
    if (!link.querySelector('svg')) {
        link.innerHTML += ' <span style="font-size:0.8em;">↗</span>';
    }
});

// ============================================
// Documentation Sidebar (for docs pages)
// ============================================
const docsSidebar = document.querySelector('.docs-sidebar');
const docsToggle = document.querySelector('.docs-toggle');

if (docsSidebar && docsToggle) {
    docsToggle.addEventListener('click', () => {
        docsSidebar.classList.toggle('active');
    });
}

// ============================================
// Search Functionality (for docs pages)
// ============================================
const searchInput = document.querySelector('.docs-search input');

if (searchInput) {
    searchInput.addEventListener('input', (e) => {
        const query = e.target.value.toLowerCase();
        const sections = document.querySelectorAll('.docs-content h2, .docs-content h3');
        
        sections.forEach(section => {
            const text = section.textContent.toLowerCase();
            const content = section.nextElementSibling;
            
            if (text.includes(query) || query === '') {
                section.style.display = 'block';
                if (content) content.style.display = 'block';
            } else {
                section.style.display = 'none';
                if (content) content.style.display = 'none';
            }
        });
    });
}

// ============================================
// Active Section Highlighting (for docs pages)
// ============================================
const docsSections = document.querySelectorAll('.docs-content h2');
const sidebarLinks = document.querySelectorAll('.docs-sidebar a');

if (docsSections.length > 0) {
    const sectionObserver = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                const id = entry.target.id;
                sidebarLinks.forEach(link => {
                    link.classList.remove('active');
                    if (link.getAttribute('href') === `#${id}`) {
                        link.classList.add('active');
                    }
                });
            }
        });
    }, {
        rootMargin: '-100px 0px -80% 0px'
    });

    docsSections.forEach(section => {
        if (section.id) {
            sectionObserver.observe(section);
        }
    });
}

console.log('HySuite website loaded successfully!');
