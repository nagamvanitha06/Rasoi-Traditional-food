import 'package:flutter/material.dart';

// Requirement GP 1: One further screen of the team's choice (Ingredient Substitutions)
class SubstitutionsScreen extends StatefulWidget {
  const SubstitutionsScreen({super.key});

  @override
  State<SubstitutionsScreen> createState() => _SubstitutionsScreenState();
}

class _SubstitutionsScreenState extends State<SubstitutionsScreen> {
  String _search = '';

  final List<Map<String, String>> _allSubstitutions = [
    {
      'original': 'Tamarind (చింతపండు / इमली)',
      'substitute': 'Amchur (Dry Mango Powder) or Lemon Juice',
      'ratio': '1 tbsp tamarind paste = 1 tsp amchur OR 1 tbsp lemon juice',
      'note': 'Amchur gives a pleasant fruity sourness ideal for sambar and curries without altering texture.'
    },
    {
      'original': 'Desi Ghee (నెయ్యి / घी)',
      'substitute': 'Cold-Pressed Mustard Oil, Coconut Oil, or Butter',
      'ratio': '1:1 equal replacement',
      'note': 'Coconut oil works best for South Indian recipes, while mustard oil suits rich North Indian gravies.'
    },
    {
      'original': 'Fresh Paneer (పనీర్ / पनीर)',
      'substitute': 'Extra Firm Pressed Tofu or Halloumi',
      'ratio': '1:1 equal weight replacement',
      'note': 'Press tofu for 15 minutes to drain water, then lightly sauté in oil before adding to gravy.'
    },
    {
      'original': 'Asafoetida / Hing (ఇంగువ / हींग)',
      'substitute': 'Garlic Powder + Onion Powder blend',
      'ratio': '1/4 tsp hing = 1/4 tsp garlic-onion powder mix',
      'note': 'If preparing Jain/sattvic food, use toasted ground cumin and ginger powder instead.'
    },
    {
      'original': 'Curry Leaves (కరివేపాకు / कढ़ी पत्ता)',
      'substitute': 'Bay Leaf (Tej Patta) + Lime Zest',
      'ratio': '6-8 curry leaves = 1 small bay leaf + 1/4 tsp lime zest',
      'note': 'Mimics the herbal and citrus aromatics during tempering (tadka).'
    },
    {
      'original': 'Jaggery (బెల్లం / गुड़)',
      'substitute': 'Palm Jaggery, Coconut Sugar, or Dark Brown Sugar',
      'ratio': '1 tbsp jaggery = 1 tbsp coconut or brown sugar',
      'note': 'Palm jaggery is especially authentic for traditional payasam, laddoos, and sweets.'
    },
  ];

  @override
  Widget build(BuildContext context) {
    final filtered = _allSubstitutions.where((s) {
      final q = _search.toLowerCase();
      return s['original']!.toLowerCase().contains(q) ||
          s['substitute']!.toLowerCase().contains(q) ||
          s['note']!.toLowerCase().contains(q);
    }).toList();

    return Scaffold(
      appBar: AppBar(
        title: const Text('Ingredient Substitutions', style: TextStyle(fontWeight: FontWeight.bold)),
        backgroundColor: const Color(0xFFBC3814),
        foregroundColor: Colors.white,
      ),
      body: Column(
        children: [
          Padding(
            padding: const EdgeInsets.all(16.0),
            child: TextField(
              decoration: InputDecoration(
                hintText: 'Search ingredient (e.g. Tamarind, Ghee, Paneer)...',
                prefixIcon: const Icon(Icons.search, color: Color(0xFFBC3814)),
                border: OutlineInputBorder(borderRadius: BorderRadius.circular(12)),
                filled: true,
                fillColor: Colors.grey.shade50,
              ),
              onChanged: (val) => setState(() => _search = val),
            ),
          ),
          Expanded(
            child: filtered.isEmpty
                ? const Center(child: Text('No matching substitutions found.'))
                : ListView.builder(
                    padding: const EdgeInsets.symmetric(horizontal: 16),
                    itemCount: filtered.length,
                    itemBuilder: (context, idx) {
                      final item = filtered[idx];
                      return Card(
                        margin: const EdgeInsets.only(bottom: 14),
                        shape: RoundedCornerShape(16),
                        elevation = 2,
                        child: Padding(
                          padding: const EdgeInsets.all(16.0),
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Row(
                                children: [
                                  const CircleAvatar(
                                    radius: 16,
                                    backgroundColor: Color(0xFFBC3814),
                                    child: Icon(Icons.swap_horiz, color: Colors.white, size: 18),
                                  ),
                                  const SizedBox(width: 10),
                                  Expanded(
                                    child: Text(
                                      item['original']!,
                                      style: const TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
                                    ),
                                  ),
                                ],
                              ),
                              const SizedBox(height: 10),
                              Container(
                                width: double.infinity,
                                padding: const EdgeInsets.all(10),
                                decoration: BoxDecoration(
                                  color: Colors.amber.shade50,
                                  borderRadius: BorderRadius.circular(8),
                                ),
                                child: Column(
                                  crossAxisAlignment: CrossAxisAlignment.start,
                                  children: [
                                    const Text(
                                      'Recommended Substitute:',
                                      style: TextStyle(fontSize: 11, fontWeight: FontWeight.bold, color: Colors.black54),
                                    ),
                                    Text(
                                      item['substitute']!,
                                      style: const TextStyle(fontSize: 14, fontWeight: FontWeight.bold, color: Colors.black87),
                                    ),
                                  ],
                                ),
                              ),
                              const SizedBox(height: 8),
                              Text(
                                'Ratio: ${item['ratio']}',
                                style: const TextStyle(fontWeight: FontWeight.w600, fontSize: 13, color: Color(0xFFBC3814)),
                              ),
                              const SizedBox(height: 4),
                              Text(
                                item['note']!,
                                style: const TextStyle(fontSize: 12, color: Colors.black54),
                              ),
                            ],
                          ),
                        ),
                      );
                    },
                  ),
          ),
        ],
      ),
    );
  }
}
